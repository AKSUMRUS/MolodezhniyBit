package com.nux.studio.studtourism.data.local.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString(TOKEN_KEY, null)
        set(value) = prefs.edit().putString(TOKEN_KEY, value).apply()

    var cloudMessagingToken: String?
        get() = prefs.getString(CLOUD_MESSAGING_TOKEN, null)
        set(value) = prefs.edit().putString(CLOUD_MESSAGING_TOKEN, value).apply()

    companion object {
        private const val PREFS_NAME = "com.nux.studio.studtourism.token"
        private const val TOKEN_KEY = "token"
        private const val CLOUD_MESSAGING_TOKEN = "cloudMessagingToken"
    }
}