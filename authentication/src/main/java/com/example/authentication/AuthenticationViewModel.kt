package com.example.authentication

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.model.AuthenticationEvent
import com.example.authentication.model.AuthenticationMode
import com.example.authentication.model.AuthenticationState
import com.example.authentication.model.PasswordRequirements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(AuthenticationState())
    val uiState: StateFlow<AuthenticationState> = _uiState

    private fun toggleAuthenticationMode() {
        val authMode = uiState.value.authenticationMode
        val newAuthMode =
            if (authMode == AuthenticationMode.SIGN_IN) AuthenticationMode.SIGN_UP else AuthenticationMode.SIGN_IN

        //The copy function in Kotlin copies the existing class reference, replacing any values that have been provided as arguments to the function.
        _uiState.value = uiState.value.copy(authenticationMode = newAuthMode)
    }

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }
            AuthenticationEvent.Authenticate -> {
                authenticate()
            }
            AuthenticationEvent.ErrorDismissed -> {
                dismissError()
            }
        }
    }

    private fun authenticate() {
        _uiState.value = uiState.value.copy(isLoading = true)
        //make network call

        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            withContext(Dispatchers.Main) {
                _uiState.value =
                    uiState.value.copy(isLoading = false, error = "Something went wrong")
            }
        }
    }

    private fun updateEmail(email: String) {
        _uiState.value = uiState.value.copy(email = email)
    }

    private fun updatePassword(password: String) {

        val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTER)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }

        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        _uiState.value = uiState.value.copy(password = password, passwordRequirements = requirements)
    }

    private fun dismissError() {
        _uiState.value = uiState.value.copy(error = null)
    }


}