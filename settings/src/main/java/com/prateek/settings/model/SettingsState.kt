package com.prateek.settings.model

data class SettingsState(
    val notificationEnabled:Boolean = false,
    val hintEnabled:Boolean = false,
    val marketingOption: MarketingOption = MarketingOption.ALLOWED,
    val themeOption: Theme = Theme.SYSTEM
)
