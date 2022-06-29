package com.example.videoexo.ui

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.videoexo.model.PlayerStatus
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun Player(
    modifier: Modifier = Modifier,
    exoPlayer: ExoPlayer,
    state: PlayerStatus = PlayerStatus.IDLE,
    context: Context
) {

    //the key is changed at any point, then the existing coroutine will be cancelled, with a new coroutine execution taking its place.
    // Using this side-effect we can prepare our ExoPlayer reference on initial composition and only as required
    LaunchedEffect(key1 = exoPlayer, block = {
        exoPlayer.prepare()
    })

    AndroidView(modifier = modifier,
        //The block creating the View to be composed,called only once
        factory = {
            StyledPlayerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                hideController()
                useController = false
                player = exoPlayer
            }
        },
        //Called once after factory and then on every state change
        update = {
            when (state) {
                PlayerStatus.PLAYING -> it.player?.play()
                PlayerStatus.PAUSED -> it.player?.pause()
            }
        })

}