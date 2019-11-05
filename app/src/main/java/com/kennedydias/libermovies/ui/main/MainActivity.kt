package com.kennedydias.libermovies.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kennedydias.libermovies.R
import com.kennedydias.libermovies.ui.base.BaseActivity
import com.kennedydias.libermovies.extensions.addFragment
import com.kennedydias.libermovies.ui.favorites.FavoritesFragment
import com.kennedydias.libermovies.ui.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override var containerId: Int = R.id.frameLayoutMain
    private val moviesFragment = MoviesFragment.newInstance()
    private val favoritesFragment = FavoritesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationMain.setOnNavigationItemSelectedListener(this)
        openFragment(moviesFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_explore -> {
                openFragment(moviesFragment)
            }
            R.id.navigation_favorite -> {
                openFragment(favoritesFragment)
            }
        }

        return true
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.addFragment(containerId, fragment)
    }

}
