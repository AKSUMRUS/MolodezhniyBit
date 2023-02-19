package com.nux.studio.studtourism.data.local.models

data class CancelBooking(
    val id: String = "",
    val status: String = "canceled",
    val dates: DatesBooked = DatesBooked(),
)
