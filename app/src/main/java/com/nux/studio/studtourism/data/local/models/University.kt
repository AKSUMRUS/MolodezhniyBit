package com.nux.studio.studtourism.data.local.models

data class University(
    val userId: String?,
    val name: String,
    val description: String?,
    val details: UniversityDetails?,
    val isDebug: Boolean = false,
    val onModeration: Boolean = false,
    val id: String,
    val timestamp: Long,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)
