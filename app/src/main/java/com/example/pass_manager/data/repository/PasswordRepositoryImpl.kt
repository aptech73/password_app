package com.example.pass_manager.data.repository

import com.example.pass_manager.data.local.PasswordDao
import com.example.pass_manager.data.local.entity.PasswordEntity
import com.example.pass_manager.data.toData
import com.example.pass_manager.data.toDomain
import com.example.pass_manager.domain.PasswordRepository
import com.example.pass_manager.domain.model.Password
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDao: PasswordDao
): PasswordRepository {
    override fun getPasswordEntity(): Flow<List<Password>> =
        passwordDao.getPasswordEntity().map { it.map { it.toDomain() } }
    
    override suspend fun insertPasswordEntity(password: Password) {
        passwordDao.insertPasswordEntity(password.toData())
    }

    override suspend fun deletePasswordEntity(password: Password) {
        passwordDao.deletePasswordEntity(password.toData())
    }

    override suspend fun deleteAllPasswordEntity() {
        passwordDao.deleteAllPasswordEntity()
    }
}