package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = tokenRepository.getToken()

        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            requestBuilder.addHeader("Authorization", token)
        }
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}