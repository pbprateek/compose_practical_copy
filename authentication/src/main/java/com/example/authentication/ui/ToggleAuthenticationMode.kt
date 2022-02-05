package com.example.authentication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.authentication.R
import com.example.authentication.model.AuthenticationMode

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthMode: () -> Unit
) {

    Surface(modifier = modifier.padding(top = 16.dp), elevation = 8.dp) {
        TextButton(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(8.dp),
            onClick = {
                toggleAuthMode()
            }) {
            Text(text = stringResource(id = if (authenticationMode == AuthenticationMode.SIGN_UP) R.string.action_need_account else R.string.action_already_have_account))

        }

    }

}