package com.kennedydias.commom.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.addFragment(viewId: Int, fragment: Fragment) {
    this.beginTransaction()
        .replace(viewId, fragment)
        .commit()
}