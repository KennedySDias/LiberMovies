package com.kennedydias.libermovies.ui.details

import android.os.Bundle
import com.kennedydias.commom.extensions.addFragment
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.ui.base.BaseActivity

class DetailsActivity : BaseActivity() {

    override var containerId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        openFragment()
    }

    private fun openFragment() {
        val fragment =
            DetailsFragment.newInstance(intent.getParcelableExtra(DetailsFragment.PARAMETER_MOVIE))
        supportFragmentManager.addFragment(containerId, fragment)
    }

}