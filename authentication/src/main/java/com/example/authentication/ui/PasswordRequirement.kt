package com.example.authentication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordRequirement(modifier: Modifier = Modifier, message: String, satisfied: Boolean) {

    val tint =
        if (satisfied) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    Row(modifier = modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = Icons.Default.Check,
            contentDescription = null, tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = message, fontSize = 12.sp, color = tint)
    }

}