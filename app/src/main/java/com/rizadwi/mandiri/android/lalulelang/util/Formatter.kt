package com.rizadwi.mandiri.android.lalulelang.util

import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject


class Formatter @Inject constructor() {
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

    fun formatIDR(amount: Int): String {
        return currencyFormat.format(amount)
    }

    fun limitText(text: String, size: Int): String {
        if (text.length < size) {
            return text
        }
        return text.slice(IntRange(0, 100)) + "..."
    }
}