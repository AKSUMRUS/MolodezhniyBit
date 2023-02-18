package com.nux.studio.studtourism.data.local.models

data class Event(
    val details: EventDetails,
    val userId: String?,
    val universityId: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long,
    val onModeration: Boolean = false,
    val id: String,
    val timestamp: Long
)
