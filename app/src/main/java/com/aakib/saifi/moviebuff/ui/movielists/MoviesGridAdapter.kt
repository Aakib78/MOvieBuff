package com.aakib.saifi.moviebuff.ui.movielists

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.aakib.saifi.moviebuff.R
import com.aakib.saifi.moviebuff.data.config.POSTER_BASE_URL
import com.aakib.saifi.moviebuff.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */
class MoviesGridAdapter( var context: Context?) : BaseAdapter() {

    private var movieList: MutableList<Movie> = ArrayList()

    fun updateItems(list: MutableList<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    var options: RequestOptions = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = this.movieList[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=inflater.inflate(R.layout.item_movie, null)

        Glide.with(context!!)
            .load(POSTER_BASE_URL+movie.posterPath)
            .apply(options)
//            .placeholder(R.drawable.spin_load)
            .into(itemView.movie_image)

        itemView?.movie_name?.text = movie.title
        return itemView
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movieList.size
    }
}