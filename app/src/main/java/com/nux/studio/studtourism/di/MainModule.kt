package com.nux.studio.studtourism.di

import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.TokenInterceptor
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://stud-api.sabir.pro"
@Module
@InstallIn(SingletonComponent::class)
interface MainModule {

    @Binds
    @Singleton
    @TokenInterceptorQualifier
    fun getTokenInterception(impl : TokenInterceptor) : Interceptor

    companion object {
        @Provides
        @Singleton
        fun provideRetrofitServices(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            @TokenInterceptorQualifier tokenInterceptor: Interceptor,
            logging: HttpLoggingInterceptor
        ) : OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        @Provides
        @Singleton
        fun provideRetrofitServices2(retrofit: Retrofit): RetrofitServices {
            return retrofit.create(RetrofitServices::class.java)
        }

    }
}