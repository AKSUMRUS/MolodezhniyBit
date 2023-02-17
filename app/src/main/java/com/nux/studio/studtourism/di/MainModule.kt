package com.nux.studio.studtourism.di

import com.nux.studio.studtourism.data.remote.TokenInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {

//    companion object {
//
//        @Provides
//        @Singleton
//        fun getDataBase( @ApplicationContext context: Context) : MyDatabase =
//            Room.databaseBuilder(
//                context = context.applicationContext,
//                MyDatabase::class.java,
//                "my_database"
//            )
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build()
//    }

    @Binds
    @Singleton
    fun getTokenInterception(impl : TokenInterceptor) : Interceptor
}