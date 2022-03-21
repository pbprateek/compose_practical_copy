package com.example.homescreen.ui.theme

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.homescreen.R
import androidx.compose.ui.res.stringResource
import com.example.homescreen.model.Destination
import com.example.homescreen.model.NavigationBarItem

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier, currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {

    NavigationRail(modifier = modifier, header = {
        FloatingActionButton(
            onClick = {
                onCreateItem()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.cd_create_item)
            )
        }
    }) {

        NavigationBarItem.buildNavigationItems(currentDestination, onNavigate).forEach {
            NavigationRailItem(
                selected = it.selected,
                onClick = it.onClick,
                icon = it.icon,
                label = it.label,
                alwaysShowLabel = false
            )

        }
    }
}
