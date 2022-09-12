package com.nbakh.ecomuser.Adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nbakh.ecomuser.R

@BindingAdapter("app:setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_placeholder_24)
            .into(imageView)
    }
}