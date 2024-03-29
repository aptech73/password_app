package com.example.pass_manager.domain

import com.example.pass_manager.data.local.entity.PasswordEntity
import com.example.pass_manager.domain.model.Password
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {
    fun getPasswordEntity() : Flow<List<Password>>
    suspend fun insertPasswordEntity(password: Password)
    suspend fun deletePasswordEntity(password: Password)
    suspend fun deleteAllPasswordEntity()
}