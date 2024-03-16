package com.example.pass_manager.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pass_manager.data.local.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], exportSchema = false, version = 1)
abstract class PasswordDataBase : RoomDatabase() {

    abstract fun getPasswordDao() : PasswordDao

    companion object {
        private const val DATABASE_NAME = "passwords.db"

        @Volatile
        private var instance: PasswordDataBase? = null

        fun getInstance(context: Context): PasswordDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): PasswordDataBase {
            return Room.databaseBuilder(context, PasswordDataBase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {})
                .build()
        }
    }
}