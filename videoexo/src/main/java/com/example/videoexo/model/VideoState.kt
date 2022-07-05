package com.example.videoexo.model

data class VideoState(
    val playerStatus: PlayerStatus = PlayerStatus.LOADING,
    val videoUri: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
)
