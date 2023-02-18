package com.nux.studio.studtourism.data.local.models

data class EventDetails(
    val dates: Dates,
    val name: String?,
    val link: String?,
    val price: String?,
    val description: String?,
    val video: List<String>?,
    val photos: List<String>?,
    val type: String?,
    val WoS: String?
)
