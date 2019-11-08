package com.kennedydias.libermovies.utils

import android.content.Context
import androidx.core.content.ContextCompat

class ResourcesUtils(
    private val context: Context
) {

    fun getDrawable(drawableId: Int) = ContextCompat.getDrawable(context, drawableId)

    fun getString(stringId: Int) = context.getString(stringId)

}