package com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizadwi.mandiri.android.lalulelang.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentAuthRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment(private val onGotoLogin: () -> Unit) :
    BaseFragment<FragmentAuthRegisterBinding>() {

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAuthRegisterBinding {
        return FragmentAuthRegisterBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        binding.tvGotoLogin.setOnClickListener { onGotoLogin.invoke() }

    }
}