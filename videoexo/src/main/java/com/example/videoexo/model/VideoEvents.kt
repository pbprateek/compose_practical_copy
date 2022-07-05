package com.example.videoexo.model

sealed class VideoEvents {

    object ToggleStatus : VideoEvents()
    object VideoLoaded : VideoEvents()
    object VideoErrors : VideoEvents()
}
