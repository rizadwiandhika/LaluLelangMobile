package com.rizadwi.mandiri.android.lalulelang.module

import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.BidRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.BidRemoteDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.BidRepository
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.BidRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractBidModule {

    @Binds
    abstract fun bindBidRemoteDataSource(impl: BidRemoteDataSourceImpl): BidRemoteDataSource

    @Binds
    abstract fun bindBidRepository(impl: BidRepositoryImpl): BidRepository


}