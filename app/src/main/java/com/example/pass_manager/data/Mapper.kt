package com.example.pass_manager.data

import com.example.pass_manager.data.local.entity.PasswordEntity
import com.example.pass_manager.domain.model.Password

fun PasswordEntity.toDomain() = Password(
    siteName = siteName,
    userName = userName,
    password = password
)