package com.example.lab1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

private const val SHARED_PREF_NAME = "appPrefSettings"
private const val KEY_LONG = "KEY_LONG"
private const val KEY_TEXT = "KEY_TEXT"

class AppSettings(context: Context) {
    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, AppCompatActivity.MODE_PRIVATE)

    fun saveLong(long: Long?) {
        if (long == null) {
            return
        }
        val editorLong = sharedPref.edit()
        editorLong.putLong(KEY_LONG, long)
        editorLong.apply()
    }

    fun saveString(text: String) {
        val editorString = sharedPref.edit()
        editorString.putString(KEY_TEXT, text)
        editorString.apply()
    }

    fun restoreLong(): Long? {
        return sharedPref.getLong(KEY_LONG, 404)
    }

    fun restoreString(): String? {
        return sharedPref.getString(KEY_TEXT, "Nothing to restore")
    }

    fun clearStorage() {
        sharedPref.edit().clear().apply()
    }
}