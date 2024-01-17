package com.rizadwi.mandiri.android.lalulelang.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigationUtil @Inject constructor() {

    fun replace(source: AppCompatActivity, destination: Class<*>) {
        source.startActivity(Intent(source, destination))
        source.finish()
    }

}