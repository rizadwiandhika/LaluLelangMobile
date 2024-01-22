package com.rizadwi.mandiri.android.lalulelang.module

import com.rizadwi.mandiri.android.lalulelang.dataaccess.local.UserLocalDataSource
import com.rizadwi.mandiri.android.lalulelang.dataaccess.local.UserLocalDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.UserRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.UserRemoteDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.UserRepositoryImpl
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