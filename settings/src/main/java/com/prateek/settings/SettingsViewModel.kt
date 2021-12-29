package com.prateek.settings

import androidx.lifecycle.ViewModel
import com.prateek.settings.model.MarketingOption
import com.prateek.settings.model.SettingsState
import com.prateek.settings.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel : ViewModel() {

    val uiState = MutableStateFlow(SettingsState())


    fun toggleNotificationSettings() {
        //The copy function in Kotlin copies the existing class reference, replacing any values that have been provided as arguments to the function.
        uiState.value = uiState.value.copy(notificationEnabled = !uiState.value.notificationEnabled)
    }

    fun toggleHintSettings() {
        uiState.value = uiState.value.copy(hintEnabled = !uiState.value.hintEnabled)
    }

    fun setMarketingSettings(option: MarketingOption) {
        uiState.value = uiState.value.copy(marketingOption = option)
    }

    fun setTheme(theme: Theme) {
        uiState.value = uiState.value.copy(themeOption = theme)
    }

}