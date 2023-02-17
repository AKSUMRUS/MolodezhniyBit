package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.local.token.TokenDao
import javax.inject.Inject

class TokenRepository @Inject constructor(
       private val tokenDao: TokenDao
) {

    fun getToken() : String {
        return tokenDao.getToken()
    }

}