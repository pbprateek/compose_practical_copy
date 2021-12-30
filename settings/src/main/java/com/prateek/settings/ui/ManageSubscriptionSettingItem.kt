package com.prateek.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prateek.settings.R


@Composable
fun ManageSubscriptionSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    onSettingClicked: () -> Unit
) {

    SettingItem(modifier = modifier) {
        Row(
            Modifier
                .clickable(
                    onClickLabel = stringResource(
                        id = R.string.cd_open_subscription
                    )
                ) {
                    onSettingClicked()
                }
                .padding(16.dp)) {
            Text(text = title, modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            ) //Here cd is not required as we are putting it on entire layout

        }

    }

}