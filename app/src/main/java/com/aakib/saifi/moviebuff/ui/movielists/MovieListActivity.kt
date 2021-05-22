package com.aakib.saifi.moviebuff.ui.movielists

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aakib.saifi.moviebuff.MainApplication
import com.aakib.saifi.moviebuff.R
import com.aakib.saifi.moviebuff.data.config.API_KEY
import com.aakib.saifi.moviebuff.databinding.ActivityMovieListBinding
import com.aakib.saifi.moviebuff.model.Movie
import com.aakib.saifi.moviebuff.ui.Router
import com.aakib.saifi.moviebuff.utils.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    private val TAG = "MovieListActivity"

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: Router

    private fun getViewModel(): MovieListViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityMovieListBinding

    private var moviesList :ArrayList<Movie>?=null

    var adapter: MoviesGridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        appComponents.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)

        init()
        initObserver()
    }

    private fun init() {
        moviesList=ArrayList()
        adapter = MoviesGridAdapter(this)
        binding.gridview.adapter = adapter
        binding.btnRefresh.setOnClickListener {
            fetchData()
        }

        binding.gridview.onItemClickListener=AdapterView.OnItemClickListener{ _, _, position, _ ->
            router.showDetailActivity(this, moviesList?.get(position)!!)
        }

        fetchData()
    }

    private fun fetchData() {
        toggleLayoutVisibility(Response.Loading)
        getViewModel().getAllMovies("popular", API_KEY)
    }

    private fun initObserver() {
        getViewModel().resultMovies.observe(this, Observer {
            if (!it.movieList.isNullOrEmpty()){
                Log.d(TAG, "initObserver: $it")
                moviesList=it.movieList
                toggleLayoutVisibility(Response.Success)
                populateMoviesRecyclerView(moviesList!!)
            }else {
                toggleLayoutVisibility(Response.Error)
            }
        })

        getViewModel().errortMovies.observe(this, Observer {
            binding.loadingView.stopShimmerAnimation()
            Log.d(TAG, "initObserver: $it")
            toggleLayoutVisibility(Response.Error)
        })

        getViewModel().loadingMovies.observe(this, Observer {
            binding.loadingView.startShimmerAnimation()
            toggleLayoutVisibility(Response.Loading)
        })
    }

    private fun toggleLayoutVisibility(response: Response){
        when(response){
            Response.Success->{
                binding.layoutNoData.hide()
                binding.gridview.show()
                binding.loadingView.hide()
            }
            Response.Error->{
                binding.layoutNoData.show()
                binding.gridview.hide()
                binding.loadingView.hide()
            }
            Response.Loading->{
                binding.layoutNoData.hide()
                binding.gridview.hide()
                binding.loadingView.show()
            }
        }
    }

    private fun populateMoviesRecyclerView(movieList: MutableList<Movie>) {
        adapter?.updateItems(movieList)
    }

    override fun onResume() {
        super.onResume()
        binding.loadingView.startShimmerAnimation()
    }

    override fun onPause() {
        binding.loadingView.stopShimmerAnimation()
        super.onPause()
    }

}