package com.rizadwi.mandiri.android.lalulelang.module

import com.rizadwi.mandiri.android.lalulelang.data.local.UserLocalDataSource
import com.rizadwi.mandiri.android.lalulelang.data.local.UserLocalDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.data.remote.UserRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.data.remote.UserRemoteDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.data.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractAuthenticationModule {

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalRepository(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    
}