package com.example.emailbox.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emailbox.InboxViewModel
import com.example.emailbox.R
import com.example.emailbox.model.InboxEvent
import com.example.emailbox.model.InboxState
import com.example.emailbox.model.InboxStatus
import com.example.emailbox.ui.theme.PracticalComposeMyCopyTheme
import kotlinx.coroutines.delay


@Composable
fun Inbox() {
    PracticalComposeMyCopyTheme {
        val viewModel: InboxViewModel = viewModel()
        EmailInbox(
            modifier = Modifier.fillMaxSize(),
            inboxState = viewModel.uiState.collectAsState().value,
            inBoxEventListener = viewModel::handleEvent
        )
        //As we only want this to happen once, not again on every recomposition
        //So Launched effect with unit as Unit will prevent relaunching this
        LaunchedEffect(Unit) {
            viewModel.handleEvent(InboxEvent.RefreshContent)
        }
    }
}

@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    inboxState: InboxState,
    inBoxEventListener: (inboxEvent: InboxEvent) -> Unit
) {

    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 0.dp) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    text = stringResource(id = R.string.inbox_emails, inboxState.emails.size),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (inboxState.status) {
                InboxStatus.LOADING -> Loading()

                InboxStatus.ERROR -> ErrorState(onTryAgainListener = {
                    inBoxEventListener(InboxEvent.RefreshContent)
                })

                InboxStatus.EMPTY -> EmptyState(checkAgainListener = {
                    inBoxEventListener(InboxEvent.RefreshContent)
                })

                InboxStatus.HAS_EMAIL -> {
                    EmailList(
                        modifier = Modifier.fillMaxSize(),
                        emails = inboxState.emails,
                        deleteEmailCallback = {
                            inBoxEventListener(InboxEvent.DeleteEmail(it))
                        })
                }

            }


        }
    }


}