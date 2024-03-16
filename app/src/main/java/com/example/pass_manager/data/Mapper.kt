package com.example.pass_manager.data

import com.example.pass_manager.data.local.entity.PasswordEntity
import com.example.pass_manager.domain.model.Password

fun PasswordEntity.toDomain() = Password(
    id = id,
    siteName = siteName,
    userName = userName,
    password = password
)

fun Password.toData() = PasswordEntity(
    id = id,
    siteName = siteName,
    userName = userName,
    password = password
)