package com.prateek.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.prateek.settings.R
import com.prateek.settings.model.MarketingOption

@Composable
fun MarketingSettingItem(
    modifier: Modifier = Modifier,
    selectedOption: MarketingOption,
    onOptionSelected: (option: MarketingOption) -> Unit
) {
    val options = stringArrayResource(id = R.array.setting_options_marketing_choice)
    SettingItem(modifier = modifier) {

        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = stringResource(id = R.string.setting_option_marketing))
            Spacer(modifier = Modifier.height(8.dp))
            options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        //Prefer selectable over clickable as it really helps in accessibility
                        //So if there are two states then use toggleable or else use selectable
                        .selectable(selected = selectedOption.id == index, onClick = {
                            val marketingOption =
                                if (index == MarketingOption.ALLOWED.id) MarketingOption.ALLOWED else MarketingOption.NOT_ALLOWED
                            onOptionSelected(marketingOption)
                        }, role = Role.RadioButton)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //For some reason if onClick is null it dosen't apply many modifiers to look good but when not null it adds proper padding and all
                    RadioButton(selected = selectedOption.id == index, onClick = null)

                    Text(
                        text = option, modifier = Modifier.padding(start = 18.dp)
                    )

                }
            }
        }
    }
}