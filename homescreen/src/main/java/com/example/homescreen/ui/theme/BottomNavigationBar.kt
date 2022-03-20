package com.example.homescreen.ui.theme

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.homescreen.model.Destination

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier) {

        listOf(Destination.Feed, Destination.Contacts, Destination.Calendar).forEach {
            BottomNavigationItem(
                selected = it.path == currentDestination.path, icon = {
                    it.icon?.let { image ->
                        Icon(
                            imageVector = image,
                            contentDescription = it.path
                        )
                    }
                },
                onClick = {
                    onNavigate(it)
                }, label = {
                    Text(text = it.path)
                }
            )
        }

    }


}