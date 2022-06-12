package com.example.emailbox.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emailbox.R


@Composable
fun EmptyState(modifier: Modifier = Modifier, checkAgainListener: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.empty_status))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = checkAgainListener) {
            Text(text = stringResource(id = R.string.label_check_again))
        }
    }
}


@Preview
@Composable
fun PreviewCheckAgain() {
    EmptyState(checkAgainListener = { })
}