package com.example.homescreen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homescreen.R
import com.example.homescreen.model.Destination
import com.example.homescreen.ui.theme.BottomNavigationBar
import com.example.homescreen.ui.theme.PracticalComposeMyCopyTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()
    //Same coroutineScope will be returned across recomposition and scope will be cancelled once call leaves the composition
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    //returns topmost backstack entry every time it changes by calling navigate or popBackStack and will cause recomposition
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentDestination by remember(navBackStackEntry) {
        //This will cache the value and return the same cached result on recomposition unless navBackStackEntry changes and causes it to recalculate
        derivedStateOf {
            navBackStackEntry.value?.destination?.route?.let {
                Destination.fromString(it)
            } ?: run { Destination.Home }
        }
    }

    Scaffold(modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "Home")
            }, actions = {
                if (currentDestination != Destination.Feed) {
                    val snackMessage = stringResource(id = R.string.not_avalible_yet)
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage)
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.Info, contentDescription = stringResource(
                                id = R.string.cd_more_information
                            )
                        )
                    }
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                //Content for fab
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                currentDestination = currentDestination,
                onNavigate = { destination ->
                    //If we just navigate it will build large collection of backstack which we don't want , something like home-contact-home-calendar - home-contact(we don't want such a backstack)
                    //So we clear the backstack
                    //The below behaviour is just like a nice twitter behaviour , the start destination acts like the point where everything starts and end


                    navController.navigate(destination.path) {
                        popUpTo(navController.graph.startDestinationId) {
                            //To save state of all the popped destinations for later restoration
                            saveState = true
                        }
                        //To avoid have duplicate destination when we click twice
                        launchSingleTop = true
                        //This works in conjuction with saveState in popUpTo ,if no state were stored there then this will have no effect
                        restoreState = true
                    }
                })
        }) {

        Navigation(modifier = modifier, navController = navController)
    }
}


@Composable
fun ContentArea(modifier: Modifier = Modifier, destination: Destination) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        destination.icon?.let {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = destination.icon,
                contentDescription = destination.path
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(text = destination.path, fontSize = 16.sp)

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticalComposeMyCopyTheme {
        Home()
    }
}