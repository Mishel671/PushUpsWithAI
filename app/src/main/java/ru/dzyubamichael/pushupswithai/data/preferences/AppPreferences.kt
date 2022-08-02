package ru.dzyubamichael.pushupswithai.data.preferences

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {

    private const val APP_PREFERENCES_KEY = "preferences"
    private const val FIRST_START_KEY = "first_start"

    private lateinit var preferences: SharedPreferences

    fun createPreferences(context: Context) {
        preferences = context.getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun saveFirstStart() {
        preferences.edit()
            .putBoolean(FIRST_START_KEY, false)
            .commit()
    }

    fun isFirstStart(): Boolean {
        return preferences.getBoolean(FIRST_START_KEY, true)
    }
}