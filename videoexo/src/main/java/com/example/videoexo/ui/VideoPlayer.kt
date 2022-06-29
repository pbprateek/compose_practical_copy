package com.example.videoexo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.videoexo.model.VideoEvents
import com.example.videoexo.model.VideoState
import com.google.android.exoplayer2.MediaItem
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer


@Composable
fun VideoPlayer(
    videoState: VideoState,
    handleEvent: (event: VideoEvents) -> Unit
) {

    val context = LocalContext.current
    val mediaItem =
        remember { MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ BigBuckBunny.mp4") }

    val exoPlayer = ExoPlayer.Builder(context).build().apply {
        setMediaItem(mediaItem)
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == Player.STATE_READY) {
                    handleEvent(VideoEvents.VideoLoaded)
                }
            }
        })
    }


}