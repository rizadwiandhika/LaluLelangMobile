package com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentAuthLoginBinding
import com.rizadwi.mandiri.android.lalulelang.util.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment(private val onGotoRegister: () -> Unit) :
    BaseFragment<FragmentAuthLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAuthLoginBinding {
        return FragmentAuthLoginBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        val loading: UIState<Nothing> = UIState.Loading


        binding.tvGotoRegister.setOnClickListener { onGotoRegister.invoke() }
    }

    fun decide(state: UIState<String>) {
        when (state) {
            UIState.Loading -> handleLoading()
            is UIState.Success -> handleSuccess(state.data)
            is UIState.Error -> handleError(state.error)
        }

    }

    private fun handleLoading() {
        print("OK")
    }

    private fun handleSuccess(data: String) {
        print("OK")
    }

    private fun handleError(err: Error) {
        print("OK")
    }
}