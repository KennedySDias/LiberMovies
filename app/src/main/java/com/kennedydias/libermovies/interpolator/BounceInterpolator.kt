package com.kennedydias.libermovies.interpolator

import kotlin.math.cos
import kotlin.math.pow

class BounceInterpolator(
    private val mAmplitude: Double = 0.5,
    private val mFrequency: Double = 5.0
) : android.view.animation.Interpolator {

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.E.pow(-time / mAmplitude) * cos(mFrequency * time) + 1).toFloat()
    }
}