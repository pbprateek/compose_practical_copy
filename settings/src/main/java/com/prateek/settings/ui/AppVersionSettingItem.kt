package com.prateek.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun AppVersionSettingItem(modifier: Modifier = Modifier, title: String, appVersion: String) {
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                //By doing this both the text view will by seen as one unit, so for things like talkback it will read the entire thing in one shot so better accessibility
                .semantics(mergeDescendants = true) {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Text(text = appVersion)

        }
    }
}