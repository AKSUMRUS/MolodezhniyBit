package com.nux.studio.studtourism.data.local.token

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class Token(
    @PrimaryKey
    val token : String
)
