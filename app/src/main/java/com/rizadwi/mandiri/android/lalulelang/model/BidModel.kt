package com.rizadwi.mandiri.android.lalulelang.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


@Parcelize
data class BidModel(
    val id: String,
    val image: Int,
    val name: String,
    val status: String,
    val description: String,
    val amount: Int,
    val deadline: Date
) : Parcelable
