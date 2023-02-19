package com.nux.studio.studtourism.data.local.models

class MainInfo (
    val name: String,
    val city: String?,
    val street: String?,
    val houseNumber: String?,
    val coordinates: Coordinates?,
    val mealPlan: String?,
    val maxDays: String?,
    val minDays: String?,
    val photos: List<String>?,
)