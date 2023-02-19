package com.nux.studio.studtourism.data.local.models

data class DormitoryBookingRequest (
    val roomId: String? = null,
    val dates: DatesBooked = DatesBooked(),
    val quantity: String? = null,
    val author: Author = Author(),
    val comment: String? = null
)