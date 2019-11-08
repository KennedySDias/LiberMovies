package com.kennedydias.libermovies.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.FragmentFavoritesBinding
import com.kennedydias.libermovies.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var moviesAdapter: FavoritesAdapter
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

        moviesAdapter = FavoritesAdapter(viewModel)

        val view = binding.root

        configureComponents()
        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
    }

    private fun configureComponents() {
        // TODO
    }

    private fun configureObservables() {
        // TODO
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

}