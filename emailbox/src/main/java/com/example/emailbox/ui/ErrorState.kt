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
fun ErrorState(modifier: Modifier = Modifier, onTryAgainListener: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.error_status))

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onTryAgainListener) {
            Text(text = stringResource(id = R.string.label_try_again))
        }
    }
}


@Preview
@Composable
fun PreviewErrorState() {
    ErrorState(onTryAgainListener = {})
}