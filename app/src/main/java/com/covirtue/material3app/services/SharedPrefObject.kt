package com.covirtue.material3app.services

import android.content.Context
import android.content.SharedPreferences

object SharedPrefObject {

    private lateinit var prefs : SharedPreferences
    private const val prefName : String = "Material3 App"

    fun saveTheme(context: Context, isDark: Boolean) {
        prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().apply{
            putBoolean("isDark", isDark)
            apply()
        }
    }

    fun getIsDark(context: Context) : Boolean? {
        prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getBoolean("isDark", false)
    }
}