package com.example.pass_manager.data.repository

import com.example.pass_manager.data.local.PasswordDao
import com.example.pass_manager.data.local.entity.PasswordEntity
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
    override fun getPasswordEntity(): Flow<List<Password>> {
        return flow {
            passwordDao.getPasswordEntity().collect {list ->
                val newList = list.map {
                    it.toDomain()
                }
                emit(newList)
            }
        }
    }
    override suspend fun insertPasswordEntity(passwordEntity: PasswordEntity) {
        passwordDao.insertPasswordEntity(passwordEntity)
    }

    override suspend fun deletePasswordEntity(passwordEntity: PasswordEntity) {
        passwordDao.deletePasswordEntity(passwordEntity)
    }

    override suspend fun deleteAllPasswordEntity() {
        passwordDao.deleteAllPasswordEntity()
    }
}