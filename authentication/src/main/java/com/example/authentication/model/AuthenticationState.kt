package com.example.authentication.model

data class AuthenticationState(
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val email: String? = null,
    val password: String? = null,
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {

    fun isFormValid(): Boolean {
        //Basically if it is sign in , don't check for password requirements
        //Only check for sign up
        return password?.isNotEmpty() == true && email?.isNotEmpty() == true && (authenticationMode ==
                AuthenticationMode.SIGN_IN || passwordRequirements.containsAll(
            PasswordRequirements.values().toList()
        ))
    }
}
