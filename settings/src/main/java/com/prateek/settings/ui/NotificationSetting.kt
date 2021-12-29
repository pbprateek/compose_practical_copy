package com.prateek.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NotificationSetting(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onToggleNotificationSettings: (checked: Boolean) -> Unit
) {

    Surface(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Switch(checked = checked, onCheckedChange = onToggleNotificationSettings)
        }
    }

}