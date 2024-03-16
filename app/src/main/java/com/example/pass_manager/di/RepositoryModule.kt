package com.example.pass_manager.di

import com.example.pass_manager.data.repository.PasswordRepositoryImpl
import com.example.pass_manager.domain.PasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePasswordRepository(
        passwordRepositoryImpl: PasswordRepositoryImpl
    ) : PasswordRepository
}