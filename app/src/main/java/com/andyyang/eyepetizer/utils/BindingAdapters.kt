package com.andyyang.eyepetizer.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("setisEnable")
var View.setisEnable
    get() = this.isEnabled
    set(value) {
        this.isEnabled = value
    }


@set:BindingAdapter("visibleOrInvisible")
var View.visibleOrInvisible
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@BindingAdapter("imageRes")
fun ImageView.setImageRes(
    @DrawableRes
    res: Int?
) {
    if (res == null || res == 0) {
        this.setImageDrawable(null)
    } else {
        this.setImageResource(res)
    }
}

@BindingAdapter("imageRawBytes", "errorRes")
fun ImageView.setImageRawBytes(imageRawBytes: ByteArray?, errorRes: Drawable?) {
    if (imageRawBytes != null) {
        try {

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = false

            val decoded = BitmapFactory.decodeByteArray(imageRawBytes, 0, imageRawBytes.size)
            this.setImageBitmap(decoded)
        } catch (e: IllegalArgumentException) {
            this.setImageDrawable(errorRes)
        }
    } else {
        this.setImageDrawable(errorRes)
    }
}

@BindingAdapter("imageBitmap")
fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
    imageView.setImageBitmap(bitmap)
}

@BindingAdapter("imageDrawable")
fun setImageDrawable(imageView: ImageView, drawable: Drawable?) {
    imageView.setImageDrawable(drawable)
}

@BindingAdapter("dataList")
fun <T> RecyclerView.setDataList(dataList: List<T>?) {
    (this.adapter as? ListAdapter<T, *>)?.submitList(dataList)
}

@BindingAdapter("imageUri", "errorRes")
fun setImageUri(imageView: ImageView, uri: Uri?, errorRes: Drawable?) {
    if (uri == null || uri.toString().isBlank()) {
        imageView.setImageDrawable(errorRes)
    } else {
        imageView.setImageURI(uri)
    }
}

