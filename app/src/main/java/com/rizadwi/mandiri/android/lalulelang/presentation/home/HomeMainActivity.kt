package com.rizadwi.mandiri.android.lalulelang.presentation.home

import android.content.Intent
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
import com.rizadwi.mandiri.android.lalulelang.util.ConfirmDialogUtil
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

    @Inject
    lateinit var confirmDialog: ConfirmDialogUtil

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when (it.itemId) {
            R.id.navigationHome -> replace(HomeFragment())
            R.id.navigationBid -> replace(BidFragment())
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
                is UIState.Success -> navigation.move(this, AuthActivity::class.java)
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
        confirmDialog.show(
            "Logging out ?",
            "Make sure everything is fine before logout. Thanks for using Lalu Lelang App!",
            "Log out",
            loginViewModel::logout
        )
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == INIT_FRAGMENT_CODE && resultCode == RESULT_OK) {
                when (data?.getIntExtra(FRAGMENT_DEST_KEY, -1)) {
                    HOME_FRAGMENT -> binding.bottomNav.selectedItemId = R.id.navigationHome
                    BID_FRAGMENT -> binding.bottomNav.selectedItemId = R.id.navigationBid
                    else -> throw Error("Unrecognized fragment destination")
                }
            }
        } catch (ex: Exception) {
            toast.show(ex.message ?: "Unknown error occurred!")
        }

    }


    override fun replace(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flHome, fragment)
            .addToBackStack(fragment::class.simpleName).commit()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val stackIndex = supportFragmentManager.backStackEntryCount - 1
        if (stackIndex < 0) {
            return finish()
        }

        val currentFragment = supportFragmentManager.getBackStackEntryAt(stackIndex)
        binding.bottomNav.setOnItemSelectedListener(null)
        when (currentFragment.name) {
            HomeFragment::class.simpleName -> binding.bottomNav.selectedItemId = R.id.navigationHome
            BidFragment::class.simpleName -> binding.bottomNav.selectedItemId = R.id.navigationBid
            else -> toast.show("Unknown fragment")
        }
        binding.bottomNav.setOnItemSelectedListener(onNavigationItemSelected)
    }

    override fun stack(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .add(R.id.flHome, fragment, fragment::class.simpleName)
            .addToBackStack(fragment::class.simpleName)
            .commit()
        return true
    }

    companion object {
        const val LOGOUT_ERR = "Something wrong when logging out"

        const val INIT_FRAGMENT_CODE = 1

        const val FRAGMENT_DEST_KEY = "FRAGMENT_DEST_KEY"

        const val HOME_FRAGMENT = 1
        const val BID_FRAGMENT = 2
    }


}