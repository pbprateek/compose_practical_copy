package com.example.videoexo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.videoexo.model.VideoEvents
import com.example.videoexo.model.VideoState
import com.google.android.exoplayer2.MediaItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.example.videoexo.R


@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoState: VideoState,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
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
    var controlsVisible by remember {
        mutableStateOf(false)
    }

    Player(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClickLabel = stringResource(id = R.string.label_display_controls)) {
                controlsVisible = !controlsVisible
            },
        exoPlayer = exoPlayer,
        context = context,
        state = videoState.playerStatus,
        lifecycleOwner = lifecycleOwner
    )

}