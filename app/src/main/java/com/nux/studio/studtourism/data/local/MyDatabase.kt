package com.nux.studio.studtourism.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nux.studio.studtourism.data.local.token.Token
import com.nux.studio.studtourism.data.local.token.TokenDao

@Database(
    entities = [Token::class],
    version = 1
)
abstract class MyDatabase: RoomDatabase() {

    abstract fun TokenDao(): TokenDao

}