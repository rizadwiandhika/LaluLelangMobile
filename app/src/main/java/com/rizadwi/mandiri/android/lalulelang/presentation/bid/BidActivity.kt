package com.rizadwi.mandiri.android.lalulelang.presentation.bid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rizadwi.mandiri.android.lalulelang.databinding.ActivityBidBinding

class BidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBidBinding.inflate(layoutInflater)

        setupUI()
    }

    private fun setupUI() {

    }
}