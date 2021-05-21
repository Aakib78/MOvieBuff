package com.aakib.saifi.moviebuff.ui.movielists

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
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
import com.aakib.saifi.moviebuff.utils.hide
import com.aakib.saifi.moviebuff.utils.show
import com.aakib.saifi.moviebuff.utils.viewModelProvider
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
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.gridview.numColumns=3
        }else {
            binding.gridview.numColumns=2
        }

        binding.btnRefresh.setOnClickListener {
            fetchData()
        }
        fetchData()
    }

    private fun fetchData() {
        binding.layoutNoData.hide()
        binding.gridview.hide()
        binding.loadingView.show()
        getViewModel().getAllMovies("popular", API_KEY)
    }

    private fun initObserver() {
        getViewModel().resultMovies.observe(this, Observer {
            if (!it.movieList.isNullOrEmpty()){
                binding.layoutNoData.hide()
                binding.gridview.show()
                binding.loadingView.hide()
                moviesList=it.movieList
                populateMoviesRecyclerView(moviesList!!)
            }else {
                binding.layoutNoData.show()
                binding.gridview.hide()
                binding.loadingView.hide()
            }

        })

        getViewModel().errortMovies.observe(this, Observer {
            binding.loadingView.stopShimmerAnimation()
            binding.layoutNoData.show()
            binding.gridview.hide()
            binding.loadingView.hide()
        })

        getViewModel().loadingMovies.observe(this, Observer {
            binding.loadingView.startShimmerAnimation()
            binding.layoutNoData.hide()
            binding.gridview.hide()
            binding.loadingView.show()
        })
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