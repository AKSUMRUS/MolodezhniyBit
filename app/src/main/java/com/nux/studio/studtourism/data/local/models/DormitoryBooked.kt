package com.nux.studio.studtourism.data.local.models

data class DormitoryBooked(
    val dates: Dates?,
    val author: Author?,
    val userId: String?,
    val universityId: String?,
    val dormitoryId: String?,
    val roomId: String?,
    val quantity: Number?,
    val comment: String?,
    val status: String?,
    val from: String?,
    val to: String?,
    val id: String,
    val timestamp: Long,
    val createdTimestamp: Long,
    val updatedTimestamp: Long,
)
