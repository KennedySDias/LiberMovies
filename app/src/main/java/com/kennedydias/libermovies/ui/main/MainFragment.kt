package com.kennedydias.libermovies.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.base.BaseFragment

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}