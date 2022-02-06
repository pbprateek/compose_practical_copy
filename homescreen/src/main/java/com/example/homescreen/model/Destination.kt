package com.example.homescreen.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val path: String, val icon: ImageVector? = null) {
    object Home : Destination("home")

    object Feed : Destination("feed", Icons.Default.List)

    object Contacts : Destination("contacts", Icons.Default.Person)

    object Calender : Destination("calender", Icons.Default.DateRange)

}
