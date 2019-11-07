package com.kennedydias.libermovies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.kennedydias.commom.extensions.observe
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.FragmentMoviesBinding
import com.kennedydias.libermovies.listener.OnSnapPositionChangeListener
import com.kennedydias.libermovies.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var seriesAdapter: MoviesAdapter
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

        moviesAdapter = MoviesAdapter(viewModel)
        seriesAdapter = MoviesAdapter(viewModel)

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureSearchView()
        viewModel.init()
    }

    private fun configureComponents() {
        configureMoviesRecyclerView()
        configureSeriesRecyclerView()
    }

    private fun configureObservables() {
        observe(viewModel.moviesOb, ::handleMoviesList)
        observe(viewModel.seriesOb, ::handleSeriesList)
        observe(viewModel.errorOb, ::handleError)
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
        observe(viewModel.seeMoreOb, ::handleSeeMore)
    }

    private fun configureMoviesRecyclerView() {
        // Configure adapter
        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }

        // Configure Snap Animation
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewMovies)

        val onSnapPositionChangeListener = object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                moviesAdapter.focusAnimation(position)
            }
        }

        val snapOnScrollListener = SnapOnScrollListener(
            snapHelper = snapHelper,
            onSnapPositionChangeListener = onSnapPositionChangeListener
        )
        binding.recyclerViewMovies.addOnScrollListener(snapOnScrollListener)
    }

    private fun configureSeriesRecyclerView() {
        // Configure adapter
        binding.recyclerViewSeries.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesAdapter
        }

        // Configure Snap Animation
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewSeries)

        val onSnapPositionChangeListener = object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                seriesAdapter.focusAnimation(position)
            }
        }

        val snapOnScrollListener = SnapOnScrollListener(
            snapHelper = snapHelper,
            onSnapPositionChangeListener = onSnapPositionChangeListener
        )
        binding.recyclerViewSeries.addOnScrollListener(snapOnScrollListener)
    }

    private fun configureSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchOb = newText
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.init()
                return true
            }

        })
        searchView.setQuery(viewModel.getInitialSearch(), true)
        searchView.clearFocus()
    }

    private fun handleMoviesList(list: List<MovieShortData>) {
        moviesAdapter.updateAll(list)
    }

    private fun handleSeriesList(list: List<MovieShortData>) {
        seriesAdapter.updateAll(list)
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun handleFatalError(message: String) {
        showToast(message)
        finish()
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog {
                viewModel.init()
            }
        } else {
            hideNotConnectedDialog()
        }
    }

    private fun handleGettingData(loading: Boolean) {
        constraintLayoutLoading.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun handleSeeMore(movie: MovieShortData) {
        // TODO
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

}
