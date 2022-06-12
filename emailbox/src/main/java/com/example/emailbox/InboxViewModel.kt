package com.example.emailbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emailbox.model.EmailFactory
import com.example.emailbox.model.InboxEvent
import com.example.emailbox.model.InboxState
import com.example.emailbox.model.InboxStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class InboxViewModel : ViewModel() {

    val uiState = MutableStateFlow(InboxState())


    fun handleEvent(contentEvent: InboxEvent) {
        when (contentEvent) {
            is InboxEvent.RefreshContent -> loadContent()
            is InboxEvent.DeleteEmail -> deleteEmail(contentEvent.id)

        }
    }

    private fun deleteEmail(id: String) {
        uiState.value = uiState.value.copy(emails = uiState.value.emails.filter {
            it.id != id
        })
    }

    private fun loadContent() {
        uiState.value = uiState.value.copy(status = InboxStatus.LOADING)

        viewModelScope.launch {
            delay(2000L)

            uiState.value = uiState.value.copy(
                status = InboxStatus.HAS_EMAIL,
                emails = EmailFactory.makeEmailList()
            )
        }

    }
}