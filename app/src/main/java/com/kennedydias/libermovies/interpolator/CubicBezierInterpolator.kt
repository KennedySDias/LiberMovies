package com.kennedydias.libermovies.interpolator

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.view.animation.Interpolator

/**
 * Class that acts as an interpolator to match the cubic-bezier css timing function where p0 is
 * fixed at 0,0 and p3 is fixed at 1,1
 */
class CubicBezierInterpolator(
    private val mX1: Float,
    private val mY1: Float,
    private val mX2: Float,
    private val mY2: Float
) : Interpolator {
    override fun getInterpolation(v: Float): Float {
        return getY(getTForXValue(v))
    }

    private fun getX(t: Float): Float {
        return getCoordinate(t, mX1, mX2)
    }

    private fun getY(t: Float): Float {
        return getCoordinate(t, mY1, mY2)
    }

    private fun getCoordinate(t: Float, p1: Float, p2: Float): Float {
        // Special case start and end.
        if (t == 0.0f || t == 1.0f) {
            return t
        }
        // Step one - from 4 points to 3.
        var ip0 = linearInterpolate(0f, p1, t)
        var ip1 = linearInterpolate(p1, p2, t)
        val ip2 = linearInterpolate(p2, 1f, t)
        // Step two - from 3 points to 2.
        ip0 = linearInterpolate(ip0, ip1, t)
        ip1 = linearInterpolate(ip1, ip2, t)
        // Final step - last point.
        return linearInterpolate(ip0, ip1, t)
    }

    private fun linearInterpolate(a: Float, b: Float, progress: Float): Float {
        return a + (b - a) * progress
    }

    private fun getTForXValue(x: Float): Float {
        val epsilon = 1e-6f
        val iterations = 8
        if (x <= 0.0f) {
            return 0.0f
        } else if (x >= 1.0f) {
            return 1.0f
        }
        // Try gradient descent to solve for t. If it works, it is very fast.
        var t = x
        var minT = 0.0f
        var maxT = 1.0f
        var value = 0.0f
        for (i in 0 until iterations) {
            value = getX(t)
            val derivative = ((getX(t + epsilon) - value) / epsilon).toDouble()
            if (Math.abs(value - x) < epsilon) {
                return t
            } else if (Math.abs(derivative) < epsilon) {
                break
            } else {
                if (value < x) {
                    minT = t
                } else {
                    maxT = t
                }
                t -= ((value - x) / derivative).toFloat()
            }
        }
        // If the gradient descent got stuck in a local minimum, e.g. because the
        // derivative was close to 0, use an interval bisection instead.
        var i = 0
        while (Math.abs(value - x) > epsilon && i < iterations) {
            if (value < x) {
                minT = t
                t = (t + maxT) / 2.0f
            } else {
                maxT = t
                t = (t + minT) / 2.0f
            }
            value = getX(t)
            i++
        }
        return t
    }
}