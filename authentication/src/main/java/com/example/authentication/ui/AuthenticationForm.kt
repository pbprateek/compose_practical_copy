package com.example.authentication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.authentication.model.AuthenticationEvent
import com.example.authentication.model.AuthenticationState

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier, authenticationState: AuthenticationState,
    handleEvent: (AuthenticationEvent) -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {




    }


}