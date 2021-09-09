package com.laco.sample.state.di

import com.laco.sample.state.data.UserRepository
import com.laco.sample.state.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface Module {

    @Binds
    fun bindUserRepository(repo: UserRepositoryImpl): UserRepository
}
