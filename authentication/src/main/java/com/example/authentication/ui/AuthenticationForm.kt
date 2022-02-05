package com.example.authentication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.model.AuthenticationEvent
import com.example.authentication.model.AuthenticationMode
import com.example.authentication.model.AuthenticationState
import com.example.authentication.model.PasswordRequirements


@Preview(showBackground = true)
@Composable
fun FormPreview() {
    AuthenticationForm(Modifier.fillMaxSize(), AuthenticationMode.SIGN_UP, null, null,
        emptyList(), true, {}, {}, {}, {})
}

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?, password: String?,
    completedPasswordRequirements: List<PasswordRequirements>,
    enableAuthentication: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onAuthenticate: () -> Unit,
    onToggleMode: () -> Unit

) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle(authenticationMode = authenticationMode)
        Spacer(modifier = Modifier.height(40.dp))


        val passwordFocusRequest = FocusRequester()
        //Just a wrapper for Surface which usages Box,default elevation 1 dp
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp), elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email,
                    onEmailChanged = onEmailChanged,
                    onNextClicked = {
                        //This will request focus for the given field on next press(imo not required if the field is just the next TextField in Row/Column)
                        passwordFocusRequest.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        //This will help in focus request on next press
                        .focusRequester(passwordFocusRequest),
                    password = password,
                    onPasswordChanged = onPasswordChanged,
                    onDonePressed = {
                        onAuthenticate()
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                    PasswordRequirements(satisfiedRequirements = completedPasswordRequirements)
                }
                Spacer(modifier = Modifier.height(12.dp))

                AuthenticationButton(
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication,
                    onAuthenticate = onAuthenticate
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        ToggleAuthenticationMode(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode,
            toggleAuthMode = onToggleMode
        )
    }

}