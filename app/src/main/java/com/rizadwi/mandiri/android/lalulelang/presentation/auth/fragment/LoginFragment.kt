package com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentAuthLoginBinding
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment(
    private val onGotoRegister: () -> Unit,
    private val onAuthenticated: () -> Unit
) :
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
        prepareView()
        observeViewModel()
    }

    private fun prepareView() {
        binding.tvGotoRegister.setOnClickListener { onGotoRegister.invoke() }
        binding.btnLogin.setOnClickListener(::authenticate)
    }

    private fun authenticate(view: View?) {
        if (!binding.btnLogin.isEnabled) {
            return
        }

        viewModel.authenticate(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun observeViewModel() {
        viewModel.loginRequest.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Error -> handleLoginError(it.error)
                UIState.Loading -> disableLoginButton()
                is UIState.Success -> handleLoginSuccess()
            }
        }

        viewModel.isAuthenticated.observe(viewLifecycleOwner) { isAuthenticated ->
            if (isAuthenticated) {
                onAuthenticated.invoke()
            }
        }
    }

    private fun handleLoginError(error: Error) {
        enableLoginButton()
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoginSuccess() {
        enableLoginButton()
        Toast.makeText(requireContext(), "Welcome!", Toast.LENGTH_SHORT).show()
        onAuthenticated.invoke()
    }

    private fun disableLoginButton() {
        binding.btnLogin.isEnabled = false
        binding.btnLogin.text = LOGIN_BTN_DISABLED_TEXT
    }

    private fun enableLoginButton() {
        binding.btnLogin.isEnabled = true
        binding.btnLogin.text = LOGIN_BTN_ENABLED_TEXT
    }

    companion object {
        const val LOGIN_BTN_DISABLED_TEXT = "Authenticating..."
        const val LOGIN_BTN_ENABLED_TEXT = "Login"
    }

}