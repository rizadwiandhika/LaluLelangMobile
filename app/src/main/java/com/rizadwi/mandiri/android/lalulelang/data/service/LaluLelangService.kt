package com.rizadwi.mandiri.android.lalulelang.data.service

import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LaluLelangService {

    @POST("auth/login")
    suspend fun authenticate(@Body data: LoginRequest): Response<Success<LoginResponse>>

    @POST("auth/register")
    suspend fun register(@Body data: RegisterUserRequest): Response<Success<RegisterUserResponse>>

    @GET("auctions")
    suspend fun getAuctions(@Header("Authorization") auth: String): Response<Success<List<AuctionResponse>>>

}