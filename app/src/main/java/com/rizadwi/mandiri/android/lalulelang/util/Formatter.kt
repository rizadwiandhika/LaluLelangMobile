package com.rizadwi.mandiri.android.lalulelang.util

import java.text.NumberFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class Formatter @Inject constructor() {
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

    fun formatIDR(amount: Int): String {
        return currencyFormat.format(amount)
    }

    fun formatDate(date: Date): String {
        val parts = date.toLocaleString().split(" ")
        val time = parts[parts.size - 1].split(":")
        return parts.subList(0, parts.size - 1).joinToString(" ") + " at " + time.subList(
            0,
            time.size - 1
        ).joinToString(":")
    }

    fun limitText(text: String, size: Int): String {
        if (text.length < size) {
            return text
        }
        return text.slice(IntRange(0, 100)) + "..."
    }
}