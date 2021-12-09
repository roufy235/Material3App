package com.covirtue.material3app

import android.app.Activity
import android.content.res.Configuration
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.covirtue.material3app.services.SharedPrefObject
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isDark : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alertBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage("Hello World!")
                .setPositiveButton("Dismiss", null)
                .setCancelable(false)
                .show()
        }

        val checkBool = SharedPrefObject.getIsDark(this)
        if (checkBool != null) {
            isDark = when(checkBool) {
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    topAppBar.setNavigationIconTint(resources.getColor(R.color.white))
                    val icon = ContextCompat.getDrawable(this, R.drawable.dark_mode);
                    icon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
                    topAppBar.menu.getItem(0).icon = icon
                    false
                }
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    topAppBar.setNavigationIconTint(resources.getColor(R.color.black))
                    val icon = ContextCompat.getDrawable(this, R.drawable.dark_mode);
                    icon?.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
                    topAppBar.menu.getItem(0).icon = icon
                    true
                }
            }
        }



        topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.toggleIcon -> {
                    isDark = !isDark
                    SharedPrefObject.saveTheme(this, isDark)
                    recreate()
                    true
                }
                else -> false
            }
        }

        fabBtn.setOnClickListener {

        }
    }

    private fun name() {

    }
}