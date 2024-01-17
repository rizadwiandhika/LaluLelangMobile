package com.rizadwi.mandiri.android.lalulelang.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.databinding.ActivityHomeBinding
import com.rizadwi.mandiri.android.lalulelang.presentation.auth.AuthActivity
import com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.BidFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.HomeFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.SettingFragment
import com.rizadwi.mandiri.android.lalulelang.util.NavigationUtil
import com.rizadwi.mandiri.android.lalulelang.util.ToastUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.type.FragmentSwitcher
import com.rizadwi.mandiri.android.lalulelang.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeMainActivity : AppCompatActivity(), FragmentSwitcher {

    private lateinit var binding: ActivityHomeBinding

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var toast: ToastUtil

    @Inject
    lateinit var navigation: NavigationUtil

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when (it.itemId) {
            R.id.navigationHome -> replace(HomeFragment())
            R.id.navigationBid -> replace(BidFragment())
            R.id.navigationSetting -> replace(SettingFragment())
            R.id.navigationLogout -> handleLogout()
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        loginViewModel.logoutAction.observe(this) {
            when (it) {
                UIState.Loading -> {}
                is UIState.Error -> toast.show(it.error.message ?: LOGOUT_ERR)
                is UIState.Success -> navigation.replace(this, AuthActivity::class.java)
            }
        }
    }

    private fun setupUI(savedInstanceState: Bundle?) {
        binding.bottomNav.setOnItemSelectedListener(onNavigationItemSelected)

        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.navigationHome
        }
    }


    private fun handleLogout(): Boolean {
        loginViewModel.logout()

        return false
    }


    override fun replace(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.flHome, fragment).commit()
        return true
    }

    override fun stack(fragment: Fragment): Boolean {
        Log.d(
            "riza",
            "added to stack: ${fragment::class.simpleName}"
        )
        supportFragmentManager.beginTransaction()
            .add(R.id.flHome, fragment, fragment::class.simpleName)
            .addToBackStack(fragment::class.simpleName)
            .commit()
        return true
    }

    companion object {
        const val LOGOUT_ERR = "Something wrong when logging out"
    }


}