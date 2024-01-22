package com.rizadwi.mandiri.android.lalulelang.module

import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.AuctionRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.AuctionRemoteDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.AuctionRepository
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.AuctionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractAuctionModule {

    @Binds
    abstract fun bindAuctionRemoteDataSource(impl: AuctionRemoteDataSourceImpl): AuctionRemoteDataSource

    @Binds
    abstract fun bindAuctionRepository(impl: AuctionRepositoryImpl): AuctionRepository


}