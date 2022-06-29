package com.example.videoexo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videoexo.VideoViewModel


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Video() {
    val viewModel: VideoViewModel = viewModel()

    VideoPlayer(
        videoState = viewModel.uiState.collectAsStateWithLifecycle().value,
        handleEvent = viewModel::handleEvents
    )

}