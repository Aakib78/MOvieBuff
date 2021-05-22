package com.aakib.saifi.moviebuff.ui.moviedetail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aakib.saifi.moviebuff.MainApplication
import com.aakib.saifi.moviebuff.R
import com.aakib.saifi.moviebuff.data.config.POSTER_BASE_URL
import com.aakib.saifi.moviebuff.databinding.ActivityMovieDetailBinding
import com.aakib.saifi.moviebuff.model.Movie
import com.aakib.saifi.moviebuff.ui.Router
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    private val TAG = "MovieListActivity"

    private val appComponents by lazy { MainApplication.appComponents }


    @Inject
    lateinit var router: Router

    private lateinit var binding: ActivityMovieDetailBinding

    private var movie:Movie?=null

    var options: RequestOptions = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        appComponents.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        movie= intent.getSerializableExtra(router.EXTRA_MOVIE_DATA) as Movie?

        initViews()
    }

    private fun initViews() {
        binding.collapsingToolbar.title=movie?.title
        binding.collapsingToolbar.setContentScrimColor(Color.RED)

        binding.movieName.text=movie?.title
        binding.tvMovieRating.text= movie?.voteAverage.toString()
        binding.tvReleaseDate.text= movie?.releaseDate
        binding.tvOverview.text= movie?.overview

        binding.btnBack.setOnClickListener {
            finish()
        }

        Glide.with(this)
            .load(POSTER_BASE_URL +movie?.backdropPath)
            .apply(options)
            .into(binding.ivBackdrop)

        Glide.with(this)
            .load(POSTER_BASE_URL +movie?.posterPath)
            .apply(options)
            .into(binding.movieImage)
    }
}