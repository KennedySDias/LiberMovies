package com.kennedydias.libermovies.ui.base

interface OnBackPressed {
    /**
     * If you return true the back press will not be taken into account, otherwise the activity will act naturally
     * So, basically, returning simulates calling super.onBackPressed()
     * @return true if your processing has priority if not false
     */
    fun onBackPressed(): Boolean
}