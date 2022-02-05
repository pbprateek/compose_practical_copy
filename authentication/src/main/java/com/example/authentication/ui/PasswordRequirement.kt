package com.example.authentication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authentication.R
import com.example.authentication.model.PasswordRequirements


@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirements>
) {

    Column(modifier = modifier) {
        PasswordRequirements.values().forEach { requirement ->
            Requirements(
                message = stringResource(id = requirement.label),
                satisfied = satisfiedRequirements.contains(requirement)
            )

        }
    }
}


@Composable
fun Requirements(modifier: Modifier = Modifier, message: String, satisfied: Boolean) {

    val requirementStatus =
        if (satisfied) stringResource(
            id = R.string.password_requirement_satisfied,
            message
        ) else stringResource(id = R.string.password_requirement_needed, message)

    val tint =
        if (satisfied) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    Row(
        modifier = modifier
            .padding(6.dp)
            .semantics(mergeDescendants = true) {
                text = AnnotatedString(text = requirementStatus)
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = Icons.Default.Check,
            contentDescription = null, tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            //Calling this here bcz we have set semantics to parent, so clearing it to the child textview
            modifier = Modifier.clearAndSetSemantics { },
            text = message,
            fontSize = 12.sp,
            color = tint
        )
    }

}