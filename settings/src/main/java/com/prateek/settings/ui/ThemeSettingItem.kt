package com.prateek.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.prateek.settings.R
import com.prateek.settings.model.Theme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThemeSettingItem(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onOptionSelected: (option: Theme) -> Unit
) {
    SettingItem(modifier = modifier) {


        //Though we keep our composable stateless, the parent has no need to know abt the state of dropdown if it's expanded or collapsed
        //And we need state so that the expanded state survives recomposition
        var expanded by remember {
            mutableStateOf(false)
        }

        Row(
            Modifier
                .clickable(onClickLabel = stringResource(id = R.string.cd_select_theme)) {
                    expanded = !expanded
                }
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.setting_option_theme),
                modifier = Modifier.weight(
                    1f
                )
            )
            Text(text = stringResource(id = selectedTheme.label))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            offset = DpOffset(16.dp, 0.dp),
            properties = PopupProperties(
                usePlatformDefaultWidth = true,
                clippingEnabled = false
            )
        ) {
            Theme.values().forEach { theme ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(theme)
                    expanded = false
                }) {
                    Text(text = stringResource(id = theme.label))
                }
            }

        }


    }
}