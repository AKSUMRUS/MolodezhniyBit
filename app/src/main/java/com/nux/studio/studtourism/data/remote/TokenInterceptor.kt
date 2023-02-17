package com.nux.studio.studtourism.data.remote

import android.content.Context
import com.nux.studio.studtourism.data.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString(TOKEN_KEY, null)

        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            requestBuilder.addHeader("Authorization", token)
        }
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    companion object {
        private const val PREFS_NAME = "com.nux.studio.studtourism.token"
        private const val TOKEN_KEY = "token"
    }

}