
package com.munchisthan.tvapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var imageView: ImageView
    private var mediaList = listOf(
        Pair("video", "https://drive.google.com/uc?id=1vt4Dw_1Z5V3TNzgu8OMHLDwdS6gvz_BX&export=download"),
        Pair("image", "https://drive.google.com/uc?id=195BYlSPo7IKywcOtGX6IrHx72VlBchoy&export=download")
    )
    private var currentMediaIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoView = VideoView(this)
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        setContentView(videoView)
        playNext()
    }

    private fun playNext() {
        if (mediaList.isEmpty()) return
        val (type, url) = mediaList[currentMediaIndex]
        currentMediaIndex = (currentMediaIndex + 1) % mediaList.size

        if (type == "video") {
            setContentView(videoView)
            videoView.setVideoURI(Uri.parse(url))
            videoView.setOnCompletionListener { playNext() }
            videoView.start()
        } else {
            setContentView(imageView)
            Glide.with(this).load(url).into(imageView)
            imageView.postDelayed({ playNext() }, 8000)
        }
    }
}
