package com.example.videoexo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videoexo.VideoViewModel


@Composable
fun Video(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val viewModel: VideoViewModel = viewModel()

    VideoPlayer(
        videoState = viewModel.uiState.collectAsStateWithLifecycle().value,
        handleEvent = viewModel::handleEvents,
        lifecycleOwner = lifecycleOwner
    )

}