package com.example.pass_manager.ui.screen.passwords_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pass_manager.domain.PasswordRepository
import com.example.pass_manager.utils.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordsViewModel @Inject constructor(
    private val passwordRepository: PasswordRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(PasswordsUiState(loadState = LoadState.LOADING))
    val uiState
        get() = _uiState.asStateFlow()

    fun loadPasswordList() {
        viewModelScope.launch {
            passwordRepository.getPasswordEntity().collect {list ->
                if (list.isEmpty()) {
                    _uiState.value = PasswordsUiState(
                        loadState = LoadState.NONEDATA
                    )
                } else {
                    _uiState.value = PasswordsUiState(
                        loadState = LoadState.SUCCESS,
                        passwords = list
                    )
                }
            }
        }
    }

}