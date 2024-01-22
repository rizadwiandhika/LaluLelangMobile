package com.rizadwi.mandiri.android.lalulelang.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigationUtil @Inject constructor() {

    fun move(source: AppCompatActivity, destination: Class<*>) {
        source.startActivity(Intent(source, destination))
        source.finish()
    }

    fun move(source: AppCompatActivity, destination: Class<*>, extra: Map<String, String>) {
        source.startActivity(Intent(source, destination).also {
            extra.forEach { (key, value) -> it.putExtra(key, value) }
        })
    }

    fun moveForResult(
        source: AppCompatActivity,
        destination: Class<*>,
        code: Int,
        extra: Map<String, String>
    ) {
        source.startActivityForResult(Intent(source, destination).also {
            extra.forEach { (key, value) -> it.putExtra(key, value) }
        }, code)
    }

}