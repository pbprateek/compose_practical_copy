package com.example.authentication.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.authentication.R
import com.example.authentication.model.AuthenticationMode

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit
) {
    Button(modifier = modifier, enabled = enableAuthentication, onClick = {
        onAuthenticate()
    }) {
        Text(text = stringResource(id = if (authenticationMode == AuthenticationMode.SIGN_UP) R.string.action_sign_up else R.string.action_sign_in))
    }
}