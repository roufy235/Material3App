package com.covirtue.material3app

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    val icon = ContextCompat.getDrawable(this, R.drawable.light_mode).apply {
                        if (Build.VERSION.SDK_INT >= 29) {
                            this?.colorFilter = BlendModeColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white), BlendMode.SRC_ATOP)
                        } else {
                            this?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white), PorterDuff.Mode.SRC_ATOP)
                        }
                    }
                    topAppBar.apply {
                        setNavigationIconTint(ContextCompat.getColor(this@MainActivity, R.color.white))
                        menu.getItem(0).icon = icon
                    }
                    false
                }
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    val icon = ContextCompat.getDrawable(this, R.drawable.dark_mode).apply {
                        if (Build.VERSION.SDK_INT >= 29) {
                            this?.colorFilter = BlendModeColorFilter(ContextCompat.getColor(this@MainActivity, R.color.black), BlendMode.SRC_ATOP)
                        } else {
                            this?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.black), PorterDuff.Mode.SRC_ATOP)
                        }
                    }
                    topAppBar.apply {
                        setNavigationIconTint(ContextCompat.getColor(this@MainActivity, R.color.black))
                        menu.getItem(0).icon = icon
                    }
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
}