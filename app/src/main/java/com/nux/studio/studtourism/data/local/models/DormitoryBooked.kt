package com.nux.studio.studtourism.data.local.models

data class DatesBooked(
    val from: String? = "2023-02-02",
    val to: String? = "2023-02-02",
)
data class DormitoryBooked(
    val dates: DatesBooked = DatesBooked(),
    val author: Author?,
    val userId: String?,
    val universityId: String?,
    val dormitoryId: String?,
    val roomId: String?,
    val quantity: Long?,
    val comment: String?,
    val status: String?,
    val from: String?,
    val to: String?,
    val id: String?,
    val timestamp: Long?,
    val createdTimestamp: Long?,
    val updatedTimestamp: Long?,
)
