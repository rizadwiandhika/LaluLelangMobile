package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizadwi.mandiri.android.lalulelang.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentBidBinding

class BidFragment : BaseFragment<FragmentBidBinding>() {

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBidBinding {
        return FragmentBidBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {

    }
}