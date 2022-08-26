package com.example.pokemon.lista

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.session.PlaybackState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun painterUrl(
    url: String,
    placeholderPainter: Painter,

): Painter {

    var state by remember { mutableStateOf(placeholderPainter) }

    if (url.isNotEmpty() && !LocalInspectionMode.current) {


        Glide.with(LocalContext.current).asBitmap().load(url).into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                resource
                    .let { state = BitmapPainter(it.asImageBitmap()) }
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                state = placeholderPainter
            }

        })

    } else state = placeholderPainter

    return state
}