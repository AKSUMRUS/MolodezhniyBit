package com.nux.studio.studtourism.data.local.models

data class DormitoryBookingRequest (
    val roomId: String? = "2",
    val dates: DatesBooked = DatesBooked(),
    val quantity: String? = "1",
    val author: Author = Author(),
    val comment: String? = "Ghbdtn"
)