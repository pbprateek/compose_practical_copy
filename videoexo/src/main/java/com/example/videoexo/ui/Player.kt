package com.example.videoexo.ui

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.videoexo.model.PlayerStatus
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun Player(
    modifier: Modifier = Modifier,
    exoPlayer: ExoPlayer,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    state: PlayerStatus = PlayerStatus.IDLE,
    context: Context
) {

    //the key is changed at any point, then the existing coroutine will be cancelled, with a new coroutine execution taking its place.
    // Using this side-effect we can prepare our ExoPlayer reference on initial composition and only as required
    //Or we would have moved exoplayer to remember in previous call
    LaunchedEffect(key1 = exoPlayer, block = {
        exoPlayer.prepare()
    })


    val currentPlayerState by rememberUpdatedState(newValue = state)

    //Disposable effects runs the effect block in 2 cases, 1st is when key changes then onDispose will rerun in onDispose we can clear the resource or reinitialise,
    // or if DisposableEffect leaves the composition,we are using the 2nd case
    DisposableEffect(
        AndroidView(modifier = modifier,
            //The block creating the View to be composed,called only once
            factory = {
                StyledPlayerView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    //We are disabling the exoplayer default controller as we will make out own
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
                    else -> it.player?.pause()
                }
            })
    ) {
        //This is trailing lambda,It take a scoped lambda DisposableEffectScope.() -> DisposableEffectResult , and onDispose is an inline function which returns DisposableEffectResult
        onDispose {
            exoPlayer.release()
        }

    }


    //We need this for our lambda to always get the latest state,So that when the above compose get's called with new state,
    // it will also update the value of currentPlayerState, If we just use state in below lambda, it will not recieve the changes data of state

    //Now we will also handle play/pause state of exoplayer when app goes into background
    //We using lifecycleOwner as key , so if the key changes it will run again and for old one we can clear
    DisposableEffect(lifecycleOwner) {

        val observer = object : LifecycleEventObserver {
            //This is a long lived lambda,recomposition will "update" the state value, without updating this object, so "state" will be old value here
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (currentPlayerState == PlayerStatus.PLAYING) {
                    if (event == Lifecycle.Event.ON_RESUME) {
                        exoPlayer.play()
                    } else if (event == Lifecycle.Event.ON_PAUSE) {
                        exoPlayer.pause()
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


}