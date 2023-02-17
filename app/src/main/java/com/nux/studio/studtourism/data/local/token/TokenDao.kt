package com.nux.studio.studtourism.data.local.token

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: String)

    @Query("SELECT token FROM token LIMIT 1")
    fun getToken() : String

}