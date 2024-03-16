package com.example.pass_manager.ui.screen.passwords_screen

import com.example.pass_manager.domain.model.Password
import com.example.pass_manager.utils.LoadState

data class PasswordsUiState (
    val loadState : LoadState = LoadState.LOADING,
    val passwords : List<Password> = listOf()
)