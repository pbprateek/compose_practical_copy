package com.example.homescreen.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.homescreen.model.Destination
import com.example.homescreen.ui.Navigation

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int
) {

    Row(modifier = modifier.fillMaxSize()) {
        if (destination.isRootDestination &&
            orientation == Configuration.ORIENTATION_LANDSCAPE
        ) {
            RailNavigationBar(currentDestination = destination, onNavigate = {
                navController.navigate(it.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }, onCreateItem = {
                navController.navigate(Destination.Creation.path)
            })
        }

        Navigation(modifier = modifier, navController = navController)
    }
}