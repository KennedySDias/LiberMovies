package com.kennedydias.libermovies.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennedydias.commom.extensions.observe
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.FragmentFavoritesBinding
import com.kennedydias.libermovies.ui.base.BaseFragment
import com.kennedydias.libermovies.ui.details.DetailsActivity
import com.kennedydias.libermovies.ui.details.DetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val viewModel by viewModel<FavoritesViewModel>()

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        favoritesAdapter = FavoritesAdapter(viewModel)

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    private fun configureComponents() {
        configureFavoritesRecyclerView()
    }

    private fun configureFavoritesRecyclerView() {
        // Configure adapter
        binding.recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }


    private fun configureObservables() {
        observe(viewModel.moviesOb, ::handleMoviesList)
        observe(viewModel.errorOb, ::handleError)
        observe(viewModel.seeMoreOb, ::handleSeeMore)
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun handleMoviesList(list: List<MovieShortData>) {
        favoritesAdapter.updateAll(list)
    }

    private fun handleSeeMore(movie: MovieShortData) {
        val newIntent = Intent(context, DetailsActivity::class.java)
        newIntent.putExtra(DetailsFragment.PARAMETER_MOVIE, movie)
        startActivity(newIntent)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

}