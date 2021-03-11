package com.ghn.shell.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imgFromId")
fun bindImageFromId(view: ImageView, resId: Int) {
    Glide.with(view.context)
            .load(resId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
}

@BindingAdapter("imgFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}
