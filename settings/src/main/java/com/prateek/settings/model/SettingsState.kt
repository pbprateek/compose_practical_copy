package com.prateek.settings.model

data class SettingsState(
    val notificationEnabled:Boolean = true,
    val hintEnabled:Boolean = false,
    val marketingOption: MarketingOption = MarketingOption.ALLOWED,
    val themeOption: Theme = Theme.SYSTEM
)
