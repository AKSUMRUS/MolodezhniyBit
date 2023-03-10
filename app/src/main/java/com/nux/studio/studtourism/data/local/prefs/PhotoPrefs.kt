package com.nux.studio.studtourism.data.local.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PhotoPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val defVal = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtxuuCDWzGaybpBF5fQJs6oTATHfPl42PH1JP2PH6D&s"

    var url: String?
        get() = prefs.getString(URL_KEY, defVal)
        set(value) = prefs.edit().putString(URL_KEY, value).apply()


    companion object {
        private const val PREFS_NAME = "com.nux.studio.studtourism.photo"
        private const val URL_KEY = "url"
    }
}