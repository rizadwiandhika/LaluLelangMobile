package com.rizadwi.mandiri.android.lalulelang.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.databinding.ActivityAuthBinding
import com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment.LoginFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.auth.fragment.RegisterFragment
import com.rizadwi.mandiri.android.lalulelang.type.FragmentReplacer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), FragmentReplacer {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        setupView()
    }

    private fun setupView() {
        gotoLogin()
    }

    private fun gotoLogin() {
        replace(LoginFragment(::gotoRegister))
    }

    private fun gotoRegister() {
        replace(RegisterFragment(::gotoLogin))
    }


    override fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl, fragment)
            .commit()
    }

}