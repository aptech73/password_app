package com.example.pass_manager.di

import android.content.Context
import com.example.pass_manager.data.local.PasswordDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePasswordDataBase(@ApplicationContext context: Context) =
        PasswordDataBase.getInstance(context)
    @Provides
    @Singleton
    fun providePasswordDao(passwordDataBase: PasswordDataBase) = passwordDataBase.getPasswordDao()
}