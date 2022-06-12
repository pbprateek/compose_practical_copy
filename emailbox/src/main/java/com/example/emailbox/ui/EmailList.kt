package com.example.emailbox.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emailbox.R
import com.example.emailbox.model.Email


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<Email>,
    deleteEmailCallback: (id: String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(emails, key = { it.id }) { email ->

            var isEmailItemDismissed by remember { mutableStateOf(false) }

            val emailHeightAnimation by animateDpAsState(
                targetValue = if (!isEmailItemDismissed) 120.dp else 0.dp,
                animationSpec = tween(delayMillis = 300),
                finishedListener = {
                    deleteEmailCallback(email.id)
                }
            )

            val dismissState = rememberDismissState(
                confirmStateChange = {
                    //So this only changes after the state change has been cnf and is irreversal
                    if (it == DismissValue.DismissedToEnd) {
                        isEmailItemDismissed = true
                    }
                    true
                }
            )

            val cardElevation = animateDpAsState(
                if (dismissState.dismissDirection != null) {
                    4.dp
                } else 0.dp
            ).value

            val dividerVisibilityAnimation by animateFloatAsState(
                targetValue = if (dismissState.targetValue ==
                    DismissValue.Default
                ) {
                    1f
                } else 0f,
                animationSpec = tween(delayMillis = 300)
            )


            SwipeToDismiss(
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = {
                    FractionalThreshold(0.30f)
                },
                dismissContent = {
                    EmailItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(emailHeightAnimation),
                        email = email, elevation = cardElevation
                    )
                },
                background = {
                    EmailItemBackground(
                        modifier = Modifier.height(emailHeightAnimation),
                        dismissState
                    )
                },
                state = dismissState
            )


            //This will result in a slight glitch kind of animation, to fix that add height animation to 0 when
            //dismiss is cnf to this as well
            Divider(
                Modifier
                    .padding(top = 16.dp)
                    .alpha(dividerVisibilityAnimation)
            )

        }
    }
}


@Composable
fun EmailItem(modifier: Modifier = Modifier, email: Email, elevation: Dp) {
    Card(modifier = modifier.padding(16.dp), elevation = elevation) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = email.title,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = email.description,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailItemBackground(modifier: Modifier = Modifier, dismissState: DismissState) {

    val backgroundColor by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            DismissValue.DismissedToEnd -> MaterialTheme.colors.error
            else -> MaterialTheme.colors.background
        },
        animationSpec = tween()
    )

    val scale by animateFloatAsState(
        targetValue = when (dismissState.targetValue) {
            DismissValue.DismissedToEnd -> 0.75f
            else -> 1f
        },
        animationSpec = tween()
    )

    val iconColor by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            DismissValue.DismissedToEnd ->
                MaterialTheme.colors.onError
            else -> MaterialTheme.colors.onSurface
        },
        animationSpec = tween(),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            modifier = Modifier.scale(scale),
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.cd_delete_email),
            tint = iconColor
        )
    }
}