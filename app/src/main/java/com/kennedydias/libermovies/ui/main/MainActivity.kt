package com.kennedydias.libermovies.ui.main

import android.os.Bundle
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.base.BaseActivity
import com.kennedydias.libermovies.extensions.addFragment

class MainActivity : BaseActivity() {

    override var containerId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        configureFragment()
    }

    private fun configureFragment() {
        val fragment = MainFragment.newInstance()
        supportFragmentManager.addFragment(containerId, fragment)
    }

}
