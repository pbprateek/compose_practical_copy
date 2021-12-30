package com.prateek.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
        SettingsList(
            state = state,
            modifier = Modifier.fillMaxSize(), viewModel = viewModel
        )

    }


}

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    viewModel: SettingsViewModel
) {

    //This modifier will persist scroll state across recomposition
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

        //part of compose material
        //Use paddingvalue to give specific padding
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(start = 12.dp)
        ) {
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

        Spacer(modifier = Modifier.height(8.dp)) //To give a shadow for app bar elevation(Fix)

        NotificationSetting(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_enable_notifications),
            checked = state.notificationEnabled,
            onToggleNotificationSettings = viewModel::toggleNotificationSettings
        )

        Divider()

        HintSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_show_hints),
            checked = state.hintEnabled,
            onShowHintToggled = viewModel::toggleHintSettings
        )
        Divider()

        ManageSubscriptionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_manage_subscription),
            onSettingClicked = {
                //Open new screen, will see later

            })

        SectionSpacer(modifier = Modifier.fillMaxWidth())

        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = viewModel::setMarketingSettings
        )

        Divider()
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = state.themeOption,
            onOptionSelected = viewModel::setTheme
        )

        SectionSpacer(modifier = Modifier.fillMaxWidth())

        AppVersionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_app_version_title),
            appVersion = stringResource(
                id = R.string.setting_app_version
            )
        )
        Divider()


    }

}