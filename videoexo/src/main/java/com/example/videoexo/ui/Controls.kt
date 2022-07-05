package com.example.videoexo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoexo.R
import com.example.videoexo.model.PlayerStatus


@Preview
@Composable
fun ControlPrev() {
    Controls(playerStatus = PlayerStatus.PLAYING, togglePlayerState = {})
}

@Composable
fun Controls(
    modifier: Modifier = Modifier,
    playerStatus: PlayerStatus,
    togglePlayerState: () -> Unit
) {
    Box(
        modifier = modifier.background(MaterialTheme.colors.surface),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                togglePlayerState()
            },
            enabled = playerStatus != PlayerStatus.LOADING
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = if (playerStatus == PlayerStatus.PLAYING) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (playerStatus == PlayerStatus.PLAYING) stringResource(id = R.string.cd_pause) else stringResource(
                    id = R.string.cd_play
                ),
                tint = MaterialTheme.colors.onSurface
            )

        }

    }
}