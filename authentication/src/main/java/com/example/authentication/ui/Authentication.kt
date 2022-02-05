package com.example.authentication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.AuthenticationViewModel
import com.example.authentication.model.AuthenticationEvent
import com.example.authentication.model.AuthenticationState


@Composable
fun Authentication() {
    MaterialTheme {
        val viewModel: AuthenticationViewModel = viewModel()

        AuthenticationContent(
            modifier = Modifier.fillMaxSize(),
            authenticationState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (AuthenticationEvent) -> Unit
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                modifier = Modifier.fillMaxSize(),
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                completedPasswordRequirements = authenticationState.passwordRequirements,
                enableAuthentication = authenticationState.isFormValid(),
                onEmailChanged = {
                    handleEvent(AuthenticationEvent.EmailChanged(it))
                },
                onPasswordChanged = {
                    handleEvent(AuthenticationEvent.PasswordChanged(it))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                onToggleMode = {
                    handleEvent(AuthenticationEvent.ToggleAuthenticationMode)
                }
            )

            authenticationState.error?.let { error ->
                AuthenticationErrorDialog(error = error, dismissError = {
                    handleEvent(AuthenticationEvent.ErrorDismissed)
                })


            }
        }

    }


}
