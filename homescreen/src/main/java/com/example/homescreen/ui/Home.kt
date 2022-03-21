package com.example.homescreen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homescreen.R
import com.example.homescreen.model.Destination
import com.example.homescreen.ui.theme.BottomNavigationBar
import com.example.homescreen.ui.theme.PracticalComposeMyCopyTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun Home(modifier: Modifier = Modifier) {

    // val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) //Pass it in scaffoldState if u need some other default initial value

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
            if (currentDestination.isRootDestination) {
                TopAppBar(title = {
                    Text(text = "Home")
                }, navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(id = R.string.cd_open_menu)
                        )
                    }
                },
                    actions = {
                        if (currentDestination != Destination.Feed) {
                            val snackMessage = stringResource(id = R.string.not_avalible_yet)
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(snackMessage)
                                }

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = stringResource(
                                        id = R.string.cd_more_information
                                    )
                                )
                            }
                        }
                    })
            }
        },
        floatingActionButton = {
            if (currentDestination.isRootDestination) {
                FloatingActionButton(onClick = {

                }) {
                    //Content for fab
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_create_item)
                    )
                }
            }
        },
        bottomBar = {
            if (currentDestination.isRootDestination) {
                BottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = { destination ->
                        //If we just navigate it will build large collection of backstack which we don't want , something like home-contact-home-calendar - home-contact(we don't want such a backstack)
                        //So we clear the backstack
                        //The below behaviour is just like a nice twitter behaviour , the start destination acts like the point where everything starts and end


                        navController.navigate(destination.path) {
                            // popUpTo(navController.graph.startDestinationId) { //This fails bcz now we using nested graph so startDestination will be Navgrapg Id and not destinationId
                            popUpTo(navController.graph.findStartDestination().id) {
                                //To save state of all the popped destinations for later restoration
                                //Idk why but also saves stack of those destination as well
                                //Suppose for destination b i opened some f and g as well, so now f and g will also be retained on stateif i navigate to b
                                //Maybe to deal with recomposition,but i don't think so(figure out)
                                saveState = false
                            }
                            //To avoid have duplicate destination when we click twice
                            launchSingleTop = true
                            //This works in conjuction with saveState in popUpTo ,if no state were stored there then this will have no effect
                            restoreState = true
                        }
                    })
            }
        },
        drawerContent = {
            if (currentDestination.isRootDestination) {
                DrawerLayout(onLogout = {

                }, onNavigationSelected = { destination ->
                    navController.navigate(destination.path)
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            }
        }
    ) {
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

@Composable
fun ColumnScope.DrawerLayout(onNavigationSelected: (Destination) -> Unit, onLogout: () -> Unit) {

    Text(
        text = stringResource(id = R.string.label_name),
        modifier = Modifier.padding(16.dp),
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = stringResource(id = R.string.label_email),
        modifier = Modifier.padding(16.dp),
        fontSize = 16.sp
    )

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )

    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Upgrade.path
    ) {
        onNavigationSelected(Destination.Upgrade)
    }
    Spacer(modifier = Modifier.height(8.dp))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Settings.path
    ) {
        onNavigationSelected(Destination.Settings)
    }
    Spacer(
        modifier = Modifier
            .weight(1f)
    )
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.log_out)
    ) {
        onLogout()
    }
    Spacer(modifier = Modifier.height(8.dp))

}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String, onClick: () -> Unit
) {
    Text(
        text = label.replaceFirstChar {
            it.titlecase(Locale.getDefault())
        }, modifier = modifier
            .clickable {
                onClick()
            }
            .padding(16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticalComposeMyCopyTheme {
        Home()
    }
}