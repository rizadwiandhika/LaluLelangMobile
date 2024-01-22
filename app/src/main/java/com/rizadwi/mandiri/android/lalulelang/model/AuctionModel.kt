package com.rizadwi.mandiri.android.lalulelang.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


@Parcelize
data class AuctionModel(
    val id: String,
    val image: Int,
    val name: String,
    val topic: String,
    val description: String,
    val price: Int,
    val deadline: Date
) : Parcelable
