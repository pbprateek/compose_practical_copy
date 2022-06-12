package com.example.emailbox.model

data class InboxState(
    val status: InboxStatus = InboxStatus.LOADING,
    val emails: List<Email> = emptyList()
)
