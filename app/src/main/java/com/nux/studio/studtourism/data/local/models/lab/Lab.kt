package com.nux.studio.studtourism.data.local.models.lab

data class Lab(
    val details: LabDetails,
    val userId: String,
    val universityId: String,
    val onModeration: Boolean = false,
    val createdTimestamp: Long,
    val updatedTimestamp: Long,
    val id: String,
    val timestamp: Long
)
