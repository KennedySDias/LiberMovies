package com.kennedydias.libermovies.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kennedydias.libermovies.R

object GlideBinding {
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadRemoteImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.shape_placeholder)
            .error(R.mipmap.ic_image_break)
            .into(view)
    }
}