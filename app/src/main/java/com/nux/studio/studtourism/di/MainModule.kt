package com.nux.studio.studtourism.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import coil.intercept.Interceptor
import com.nux.studio.studtourism.data.local.MyDatabase
import com.nux.studio.studtourism.data.remote.TokenInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface MainModule {


    companion object {


        @Provides
        @Singleton
        fun getDataBase( @ApplicationContext context: Context) : MyDatabase =
            Room.databaseBuilder(
                context = context.applicationContext,
                MyDatabase::class.java,
                "my_database"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }

    @Binds
    @Singleton
    fun getTokenInterception(token : TokenInterceptor) : Interceptor

}