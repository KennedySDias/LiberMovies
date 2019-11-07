package com.kennedydias.libermovies.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.ListItemMoviesBinding
import com.kennedydias.libermovies.interpolator.CubicBezierInterpolator

class MoviesAdapter(
    private val viewModel: MoviesViewModel
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val movies: MutableList<MovieShortData> = mutableListOf()
    private var focusedItem = 0

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
        binding?.viewModel = viewModel
        binding?.movie = movies[position]
        binding?.isFocusedItem = (focusedItem == position)
        binding?.executePendingBindings()
    }

    fun updateAll(list: List<MovieShortData>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun focusAnimation(position: Int) {
        focusedItem = position
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemMoviesBinding? = DataBindingUtil.bind(itemView)
    }

}

@BindingAdapter("android:setAnimatedFocus")
fun setAnimatedFocus(target: View, isFocused: Boolean) {
    if (isFocused) {
        target.animate()
            .scaleX(1.05f)
            .scaleY(1.05f)
            .setInterpolator(CubicBezierInterpolator(.5f, .5f, .1f, 1f))
            .start()
    } else {
        target.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setInterpolator(CubicBezierInterpolator(.5f, .5f, .1f, 1f))
            .start()
    }
}