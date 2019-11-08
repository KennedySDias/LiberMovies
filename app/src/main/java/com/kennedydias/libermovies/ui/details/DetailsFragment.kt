package com.kennedydias.libermovies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.kennedydias.commom.extensions.observe
import com.kennedydias.domain.model.MovieShortData
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.FragmentDetailsBinding
import com.kennedydias.libermovies.interpolator.BounceInterpolator
import com.kennedydias.libermovies.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide StatusBar from Android
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(arguments)
    }

    private fun configureComponents() {
        // Show FAB with animation
        binding.fab.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .setInterpolator(BounceInterpolator())
            .start()
    }

    private fun configureObservables() {
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.errorOb, ::handleError)
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
        observe(viewModel.isFavorite, ::handleIsFavorite)
    }

    private fun handleFatalError(message: String) {
        showToast(message)
        finish()
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog {
                viewModel.getDetails()
            }
        } else {
            hideNotConnectedDialog()
        }
    }

    private fun handleGettingData(loading: Boolean) {
        if (loading) {
            binding.groupMovieData.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.groupMovieData.visibility = View.VISIBLE
        }
    }

    private fun handleIsFavorite(isFavorite: Boolean) {
        context?.let { context ->
            if (isFavorite) {
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_favorite_border
                    )
                )
            }
        }
    }

    companion object {
        const val PARAMETER_MOVIE = "PARAMETER_MOVIE"

        fun newInstance(movie: MovieShortData?): DetailsFragment {
            val args = Bundle()
            args.putParcelable(PARAMETER_MOVIE, movie)

            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}