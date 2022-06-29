package com.example.videoexo

import androidx.lifecycle.ViewModel
import com.example.videoexo.model.PlayerStatus
import com.example.videoexo.model.VideoEvents
import com.example.videoexo.model.VideoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(VideoState())
    val uiState: StateFlow<VideoState> = _uiState


    fun handleEvents(event: VideoEvents) {
        when (event) {
            VideoEvents.VideoLoaded -> {
                _uiState.value = _uiState.value.copy(playerStatus = PlayerStatus.IDLE)
            }
            VideoEvents.VideoErrors -> {
                _uiState.value = _uiState.value.copy(playerStatus = PlayerStatus.ERROR)
            }
            VideoEvents.ToggleStatus -> toggleVideoStatus()

        }
    }

    private fun toggleVideoStatus() {
        val playerStatus = _uiState.value.playerStatus
        val newPlayingStatue = if (playerStatus != PlayerStatus.PLAYING) {
            PlayerStatus.PLAYING
        } else
            PlayerStatus.PAUSED

        _uiState.value = _uiState.value.copy(playerStatus = newPlayingStatue)
    }

}