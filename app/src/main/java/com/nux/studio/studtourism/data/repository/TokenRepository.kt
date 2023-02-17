package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.local.prefs.TokenPrefs
import javax.inject.Inject

class TokenRepository @Inject constructor(
       private val tokenPrefs: TokenPrefs
) {

    fun getToken() : String? {
        return tokenPrefs.token
    }

}