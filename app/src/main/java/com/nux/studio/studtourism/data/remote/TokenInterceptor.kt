package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
            val token : String = tokenRepository.getToken()

            val request = chain.request().newBuilder().addHeader("Authorization", token).build()

        return chain.proceed(request)
    }

}