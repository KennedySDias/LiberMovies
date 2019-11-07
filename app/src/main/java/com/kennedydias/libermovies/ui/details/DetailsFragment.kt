package com.kennedydias.libermovies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.databinding.FragmentDetailsBinding
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        binding.lifecycleOwner = this

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
        fun newInstance(): DetailsFragment {
            val fragment = DetailsFragment()
            return fragment
        }
    }
}