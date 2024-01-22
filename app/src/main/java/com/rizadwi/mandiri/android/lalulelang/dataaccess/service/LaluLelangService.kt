package com.rizadwi.mandiri.android.lalulelang.dataaccess.service

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LaluLelangService {

    @POST("auth/login")
    suspend fun authenticate(@Body data: LoginRequest): Response<Success<LoginResponse>>

    @POST("auth/register")
    suspend fun register(@Body data: RegisterUserRequest): Response<Success<RegisterUserResponse>>

    @GET("auctions")
    suspend fun getAuctions(@Header("Authorization") auth: String): Response<Success<List<AuctionResponse>>>

    @GET("auctions/{id}")
    suspend fun getAuctionById(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ): Response<Success<AuctionResponse>>

    @POST("auctions/{id}/bid")
    suspend fun createBid(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body data: BidRequest
    ): Response<Success<BidResponse>>

    @GET("bid")
    suspend fun getMyBidList(@Header("Authorization") auth: String): Response<Success<List<BidResponse>>>

    @POST("purchases/{id}/deliveries")
    suspend fun createDelivery(
        @Header("Authorization") auth: String,
        @Path("id") purchaseId: String,
        @Body data: CreateDeliveryRequest
    ): Response<Success<CreateDeliveryResponse>>


    @GET("courier")
    suspend fun getCourierList(@Header("Authorization") auth: String): Response<Success<List<CourierResponse>>>


}