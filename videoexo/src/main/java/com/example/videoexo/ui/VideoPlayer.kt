package com.example.videoexo.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.videoexo.model.VideoEvents
import com.example.videoexo.model.VideoState
import com.google.android.exoplayer2.MediaItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.example.videoexo.R
import com.example.videoexo.model.PlayerStatus


@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoState: VideoState,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    handleEvent: (event: VideoEvents) -> Unit
) {

    val context = LocalContext.current

    val exoPlayer = remember(key1 = videoState.videoUri) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoState.videoUri))
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

    Box(modifier = Modifier.background(Color.Black)) {

        var controlsVisible by remember {
            mutableStateOf(true)
        }

        val alphaAnimation by animateFloatAsState(
            targetValue = if (controlsVisible) 0.7f else 0f,
            animationSpec = if (controlsVisible) {
                tween(delayMillis = 0)
            } else
                tween(delayMillis = 750) // So that control gives some time

        )

        //We will put this first as we want to slot controls on top of player
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

        Controls(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.BottomCenter)
                .alpha(alphaAnimation),
            playerStatus = videoState.playerStatus,
            togglePlayerState = {
                //Right now if status if Paused ,So if we click it will become playing,but after we update the state
                //So for now check using opposite
                if (videoState.playerStatus != PlayerStatus.PLAYING)
                    controlsVisible = false
                handleEvent(VideoEvents.ToggleStatus)
            })
    }
}