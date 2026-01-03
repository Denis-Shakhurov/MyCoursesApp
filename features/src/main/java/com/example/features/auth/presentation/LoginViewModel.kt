package com.example.features.auth.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
        validateForm()
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
        validateForm()
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _state.update { it.copy(isLoginEnabled = true) }
        }
    }

    private fun validateForm() {
        val currentState = _state.value
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches()
        val isPasswordValid = currentState.password.isNotEmpty()
        val isFormValid = isEmailValid && isPasswordValid

        _state.update { it.copy(isLoginEnabled = isFormValid) }
    }
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false
)