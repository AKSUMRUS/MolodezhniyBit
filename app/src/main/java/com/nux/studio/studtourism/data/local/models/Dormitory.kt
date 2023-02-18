package com.nux.studio.studtourism.data.local.models

import android.util.Log

class Dormitory (
    val userId: String?,
    val universityId: String?,
    val createdTimestamp: Long?,
    val details: DormitoryDetails?,
    val rooms: Map<String, Room>?,
    val onModeration: Boolean?,
    val id: String?,
    val timestamp: Long?,
    val updatedTimestamp: Long?,
)