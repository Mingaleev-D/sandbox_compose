package com.example.sandbox_compose.ui.pages.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.Graph
import com.example.sandbox_compose.data.repository.AuthRepository
import com.example.sandbox_compose.utils.Resource
import com.example.sandbox_compose.utils.collectAndHandle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpViewModel(
       private val repository: AuthRepository = Graph.authRepository
) : ViewModel() {

    var signUpState by mutableStateOf(SignUpState())
        private set

    companion object {

        const val TAG = "signupVM"
    }

    fun signUpEvent(signUpEvents: SignUpEvents) {
        when (signUpEvents) {
            is SignUpEvents.onEmailChange -> {
                signUpState = signUpState.copy(
                       email = signUpEvents.email
                )
            }

            is SignUpEvents.onFirstNameChange -> {
                signUpState = signUpState.copy(
                       firstName = signUpEvents.firstName
                )
            }

            is SignUpEvents.onLastNameChange -> {
                signUpState = signUpState.copy(
                       lastName = signUpEvents.lastName
                )
            }

            is SignUpEvents.onPaswwordChange -> {
                signUpState = signUpState.copy(
                       password = signUpEvents.password
                )
            }

            is SignUpEvents.onConfirmPasswordChange -> {
                signUpState = signUpState.copy(
                       confirmPassword = signUpEvents.confirmPassword
                )
            }

            is SignUpEvents.onAgreeTermsChange -> {
                signUpState = signUpState.copy(
                       agreeTerms = signUpEvents.agree
                )
            }

            is SignUpEvents.SignUp -> {
                createUser()
            }

            is SignUpEvents.OnIsEmailVerificationChange -> {
                signUpState = signUpState.copy(
                       isVerificationEmailSent = false
                )
            }
        }
    }

    private fun createUser() = viewModelScope.launch {
        try {
            val isNotSamePassword: Boolean = signUpState.password != signUpState.confirmPassword
            if (!validateSignUpForm()) throw IllegalArgumentException("Fields Can not be empty")
            if (isNotSamePassword) throw IllegalArgumentException("Password do not Match")
            signUpState = signUpState.copy(
                   isLoading = true,
                   loginErrorMsg = null
            )
            repository.createUser(signUpState.email, signUpState.password).collectLatest {
                signUpState = when (it) {
                    is Resource.Loading -> {
                        signUpState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        sendVerificationEmail()
                        signUpState.copy(isSuccessLogin = true, isLoading = false)
                    }

                    is Resource.Error -> {
                        signUpState = signUpState.copy(isSuccessLogin = false, isLoading = false)
                        throw IllegalArgumentException(it.throwable)
                    }
                }
            }
        } catch (e: Exception) {
            signUpState = signUpState.copy(
                   loginErrorMsg = e.localizedMessage
            )
        } finally {
            signUpState = signUpState.copy(
                   isLoading = false
            )
        }
    }

    private fun sendVerificationEmail() = repository.sendEmailVerification(
           onSuccess = { signUpState = signUpState.copy(isVerificationEmailSent = true) },
           onError = { throw it ?: Throwable("Unknown Error") }
    )

    private fun validateSignUpForm() = signUpState.run {
        firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
        && password.isNotEmpty() && confirmPassword.isNotEmpty() && agreeTerms
    }
}

data class SignUpState(
       val firstName: String = "",
       val lastName: String = "",
       val email: String = "",
       val password: String = "",
       val confirmPassword: String = "",
       val agreeTerms: Boolean = false,
       val isLoading: Boolean = false,
       val isSuccessLogin: Boolean = false,
       val isVerificationEmailSent: Boolean = false,
       val loginErrorMsg: String? = null,
)

sealed class SignUpEvents {
    data class onEmailChange(val email: String) : SignUpEvents()
    data class onFirstNameChange(val firstName: String) : SignUpEvents()
    data class onLastNameChange(val lastName: String) : SignUpEvents()
    data class onPaswwordChange(val password: String) : SignUpEvents()
    data class onConfirmPasswordChange(val confirmPassword: String) : SignUpEvents()
    data class onAgreeTermsChange(val agree: Boolean) : SignUpEvents()
    data object SignUp : SignUpEvents()
    data object OnIsEmailVerificationChange : SignUpEvents()
}
