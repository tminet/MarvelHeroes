package tmidev.marvelheroes.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import tmidev.marvelheroes.R

interface ImageLoader {
    fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes placeholder: Int = R.drawable.ic_image_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_image_error
    )
}