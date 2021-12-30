package com.prateek.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prateek.settings.R


@Composable
fun NotificationSetting(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onToggleNotificationSettings: () -> Unit
) {

    val notificationsEnabledState = if (checked) {
        stringResource(id = R.string.cd_notifications_enabled)
    } else stringResource(id = R.string.cd_notifications_disabled)

    SettingItem(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = { onToggleNotificationSettings() },
                    role = Role.Switch
                )
                //This will provide accessibility based on the correct state
                .semantics {
                    stateDescription = notificationsEnabledState
                }
                .padding(horizontal = 16.dp) //This order matters as with this on top,it will put toggle on centre area basically the area we cut off with padding will not have toggle
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            //The state of the switch depends totally on the "checked", touching UI will not change the state, it can just call the lambda
            Switch(checked = checked, onCheckedChange = null)
        }
    }

}

@Preview
@Composable
fun Preview_NotificationSettings() {
    MaterialTheme {
        NotificationSetting(
            modifier = Modifier.fillMaxWidth(),
            title = "Enable Notifications",
            checked = true,
            onToggleNotificationSettings = { }
        )
    }
}