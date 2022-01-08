package com.example.authentication.model

import androidx.annotation.StringRes
import com.example.authentication.R

enum class PasswordRequirements(@StringRes val label: Int) {
    CAPITAL_LETTER(R.string.password_requirement_capital), NUMBER(R.string.password_requirement_digit), EIGHT_CHARACTER(
        R.string.password_requirement_characters
    )
}