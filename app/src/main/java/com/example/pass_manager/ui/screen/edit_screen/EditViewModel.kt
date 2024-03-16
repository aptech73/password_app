package com.example.pass_manager.ui.screen.edit_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pass_manager.domain.PasswordRepository
import com.example.pass_manager.domain.model.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val passwordRepository: PasswordRepository
): ViewModel() {

    fun deletePasswordEntity(password: Password) {
        viewModelScope.launch {
            passwordRepository.deletePasswordEntity(password)
        }
    }

    fun insertPasswordEntity(password: Password) {
        viewModelScope.launch {
            passwordRepository.insertPasswordEntity(password)
        }
    }
}