package com.example.homescreen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homescreen.model.Destination


@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController) {

    //NavHostController-This is a class that is used to control the navigation of our app - so if the user performs an action that should trigger a navigation event then we will use this controller to instruct our navigation graph accordingly.
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home.path //Bcz we are nesting , it will look for this route and there Feed is the startDestination(like django)
    ) {

        //Use the builder to create the graph,composable is an extension function of NavGraphBuilder helps us add compose to the graph and pass parameters

        //The NavHost also supports nested navigation graphs, which allows us to group sets of related destinations together.Do this using navigation extension
        navigation(startDestination = Destination.Feed.path, route = Destination.Home.path) {
            //Default Start Destination
            composable(Destination.Feed.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Feed)
            }
            composable(Destination.Contacts.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Contacts)
            }
            composable(Destination.Calendar.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Calendar)
            }
        }

        navigation(startDestination = Destination.Add.path, route = Destination.Creation.path) {
            composable(Destination.Add.path) {
                ContentArea(destination = Destination.Add, modifier = Modifier.fillMaxSize())
            }
        }


        composable(Destination.Settings.path) {
            ContentArea(destination = Destination.Settings, modifier = Modifier.fillMaxSize())
        }

        composable(Destination.Upgrade.path) {
            ContentArea(destination = Destination.Upgrade, modifier = Modifier.fillMaxSize())
        }

    }
}

