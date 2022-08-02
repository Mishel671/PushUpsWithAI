package ru.dzyubamichael.pushupswithai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.dzyubamichael.pushupswithai.data.preferences.AppPreferences

@HiltAndroidApp
class TrainingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.createPreferences(this)
    }
}