package com.rizadwi.mandiri.android.lalulelang.module

import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.DeliveryRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.DeliveryRemoteDataSourceImpl
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.DeliveryRepository
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.DeliveryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractDeliveryModule {

    @Binds
    abstract fun bindDeliveryRemoteDataSource(impl: DeliveryRemoteDataSourceImpl): DeliveryRemoteDataSource

    @Binds
    abstract fun bindDeliveryRepository(impl: DeliveryRepositoryImpl): DeliveryRepository


}