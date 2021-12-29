package com.prateek.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prateek.settings.R
import com.prateek.settings.SettingsViewModel
import com.prateek.settings.model.SettingsState

@Composable
fun Settings() {

    val viewModel: SettingsViewModel = viewModel()
    MaterialTheme() {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(state = state)

    }


}

@Composable
fun SettingsList(modifier: Modifier = Modifier, state: SettingsState) {

    //This modifier will persist scroll state across recomposition
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

        //part of compose material
        //Use paddingvalue to give specific padding
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(start = 12.dp)
        ) {

            Row() {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_go_back)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.settings),
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onSurface
                )
            }

        }

        NotificationSetting(
            title = "Enable Notification",
            checked = true,
            onToggleNotificationSettings = {}
        )

    }

}