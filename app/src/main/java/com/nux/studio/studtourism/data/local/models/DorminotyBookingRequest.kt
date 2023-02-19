package com.nux.studio.studtourism.data.local.models

data class DormitoryBookingRequest (
    val roomId: String? = "",
    val dates: DatesBooked = DatesBooked(),
    val quantity: String? = "",
    val author: Author = Author(),
    val comment: String? = ""
)