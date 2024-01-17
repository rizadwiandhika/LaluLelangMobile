package com.rizadwi.mandiri.android.lalulelang.util.type

import androidx.fragment.app.Fragment

interface FragmentSwitcher {
    fun replace(fragment: Fragment): Boolean

    fun stack(fragment: Fragment): Boolean
}