package com.example.exoplayerapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity(), Player.Listener {
    private var player: ExoPlayer? = null
    var isPipMode: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        player?.release()
    }

    override fun onResume() {
        super.onResume()
        if (player!!.isPlaying) {
            isPipMode = false
        }
    }

    private fun initializePlayer() {
        val exoPlayer: PlayerView = findViewById(R.id.playerView)
        player = ExoPlayer.Builder(applicationContext).build()
        exoPlayer.player = player
        exoPlayer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

        val mediaItem: MediaItem =
            MediaItem.fromUri("http://techslides.com/demos/sample-videos/small.mp4")
        player!!.setMediaItem(mediaItem)
        player!!.prepare()
        player!!.play()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBackPressed() {
        if (!isPipMode!!) {
            enterPictureInPictureMode()
        } else {
            super.onBackPressed()
        }
    }
}