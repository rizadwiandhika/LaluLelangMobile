package com.rizadwi.mandiri.android.lalulelang.presentation.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.databinding.ActivityAuthBinding
import com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment.LoginFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment.RegisterFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.home.HomeMainActivity
import com.rizadwi.mandiri.android.lalulelang.util.NavigationUtil
import com.rizadwi.mandiri.android.lalulelang.util.type.FragmentSwitcher
import com.rizadwi.mandiri.android.lalulelang.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), FragmentSwitcher {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var navigation: NavigationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIfAuthenticated()
        observeViewModel()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun observeViewModel() {
        viewModel.isAuthenticated.observe(this) {
            if (it) {
                navigation.move(this, HomeMainActivity::class.java)
            }
        }
    }

    private fun setupView() {
        gotoLogin()
    }

    private fun checkIfAuthenticated() {
        viewModel.checkIfAuthenticated()
    }

    private fun gotoLogin() {
        replace(LoginFragment(::gotoRegister, ::handleAuthenticated))
    }

    private fun gotoRegister() {
        replace(RegisterFragment(::gotoLogin))
    }

    private fun handleAuthenticated() {
        navigation.move(this, HomeMainActivity::class.java)
    }


    override fun replace(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.flAuth, fragment).commit()
        return true
    }

    override fun stack(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .add(R.id.flAuth, fragment, fragment::class.simpleName)
            .addToBackStack(fragment::class.simpleName)
            .commit()
        return true
    }

}