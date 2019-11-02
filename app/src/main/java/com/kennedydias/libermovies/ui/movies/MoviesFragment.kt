package com.kennedydias.libermovies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.base.BaseFragment
import com.kennedydias.libermovies.databinding.FragmentMoviesBinding
import com.kennedydias.libermovies.extensions.observe
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    private var moviesAdapter = MoviesAdapter()
    private var seriesAdapter = MoviesAdapter()
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
    }

    private fun configureComponents() {
        configureMoviesRecyclerView()
        configureSeriesRecyclerView()
    }

    private fun configureObservables() {
        observe(viewModel.movies, ::handleMoviesList)
        observe(viewModel.series, ::handleSeriesList)
    }

    private fun configureMoviesRecyclerView() {
        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
    }

    private fun configureSeriesRecyclerView() {
        binding.recyclerViewSeries.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesAdapter
        }
    }

    private fun handleMoviesList(list: List<MovieData>) {
        moviesAdapter.updateAll(list)
    }

    private fun handleSeriesList(list: List<MovieData>) {
        seriesAdapter.updateAll(list)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

}
