package com.rizadwi.mandiri.android.lalulelang.module

import com.google.gson.Gson
import com.rizadwi.mandiri.android.lalulelang.data.service.LaluLelangService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.43.61:8080/api/v1/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideLaluLelangService(retrofit: Retrofit): LaluLelangService {
        return retrofit.create(LaluLelangService::class.java)
    }
}