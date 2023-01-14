package com.example.musicdashboard.model

data class MusicDashboardState(
    val status: Status = Status.LOADING,
    val tracks:List<Track> = emptyList()
)
