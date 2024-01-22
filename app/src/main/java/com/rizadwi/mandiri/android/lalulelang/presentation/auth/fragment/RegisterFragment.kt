package com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentAuthRegisterBinding
import com.rizadwi.mandiri.android.lalulelang.util.ToastUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.auth.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment(private val onGotoLogin: () -> Unit) :
    BaseFragment<FragmentAuthRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var toast: ToastUtil

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAuthRegisterBinding {
        return FragmentAuthRegisterBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.tvGotoLogin.setOnClickListener { onGotoLogin.invoke() }
        binding.btnRegister.setOnClickListener { handleRegistration() }
    }

    private fun handleRegistration() {
        with(binding) {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val retypedPassword = etPasswordVerify.text.toString()

            viewModel.register(name, email, password, retypedPassword)
        }
    }

    private fun observeViewModel() {
        viewModel.registerRequest.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Error -> {
                    enableRegisterButton()
                    toast.show(it.error.message ?: "Something went wrong")
                }

                UIState.Loading -> {
                    disableRegisterButton()
                }

                is UIState.Success -> {
                    enableRegisterButton()
                    toast.show("Register success!")
                    onGotoLogin.invoke()
                }
            }
        }
    }


    private fun disableRegisterButton() {
        binding.btnRegister.isEnabled = false
        binding.btnRegister.text = REGISTER_BTN_DISABLED_TEXT
    }

    private fun enableRegisterButton() {
        binding.btnRegister.isEnabled = true
        binding.btnRegister.text = REGISTER_BTN_ENABLED_TEXT
    }

    companion object {
        const val REGISTER_BTN_DISABLED_TEXT = "Registering..."
        const val REGISTER_BTN_ENABLED_TEXT = "Create account"
    }


}