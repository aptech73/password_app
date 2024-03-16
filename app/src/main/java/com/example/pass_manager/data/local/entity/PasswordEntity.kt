package com.example.pass_manager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntity (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val siteName : String,
    val userName : String,
    val password : String
)