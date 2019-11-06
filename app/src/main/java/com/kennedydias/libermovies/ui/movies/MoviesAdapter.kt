package com.kennedydias.libermovies.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.ListItemMoviesBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val movies: MutableList<MovieShortData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val binding = holder.binding
        binding?.movie = movies[position]
        binding?.executePendingBindings()
    }

    fun updateAll(list: List<MovieShortData>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemMoviesBinding? = DataBindingUtil.bind(itemView)
    }

}