package com.example.pass_manager.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pass_manager.data.local.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Delete
    suspend fun deletePasswordEntity(passwordEntity: PasswordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPasswordEntity(passwordEntity: PasswordEntity)

    @Query("SELECT * FROM passwords ORDER BY siteName")
    fun getPasswordEntity() : Flow<List<PasswordEntity>>

    @Query("DELETE FROM passwords")
    suspend fun deleteAllPasswordEntity()
}