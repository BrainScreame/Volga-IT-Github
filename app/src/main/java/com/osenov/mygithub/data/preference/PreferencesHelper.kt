package com.osenov.mygithub.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.osenov.mygithub.PREF_FILE_NAME
import com.osenov.mygithub.TOKEN
import com.osenov.mygithub.di.scope.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor {
        return preferences.edit()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun setToken(token: String?) {
        getEditor().putString(TOKEN, token).commit()
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN, "")
    }
}