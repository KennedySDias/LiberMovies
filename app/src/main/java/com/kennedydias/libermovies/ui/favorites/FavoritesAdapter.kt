package com.kennedydias.libermovies.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.ListItemFavoritesBinding

class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val movies: MutableList<MovieShortData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_favorites,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val binding = holder.binding
        binding?.viewModel = viewModel
        binding?.movie = movies[position]
        binding?.executePendingBindings()
    }

    fun updateAll(list: List<MovieShortData>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemFavoritesBinding? = DataBindingUtil.bind(itemView)
    }

}