package com.example.homescreen.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.homescreen.R

@Composable
fun Home(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "Home")
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
        }) {


    }
}