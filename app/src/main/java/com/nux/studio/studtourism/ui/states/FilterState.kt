package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Committee

data class FilterState(
    val nameBy: SortOrder = SortOrder.NONE,
    val ratingBy: SortOrder = SortOrder.NONE,
    val city: String? = "",
    val district: String? = "",
    val subject: String? = "",
    val committee: Committee? = null,
    val placeType: String? = "",
    val mealType: MealType = MealType.BREAKFAST,
    val startDate: String = "",
    val endDate: String = "",
)

enum class SortOrder {
    DESCENDING,
    ASCENDING,
    NONE,
}

enum class MealType {
    BREAKFAST,
    DINNER,
    BREAKFAST_AND_DINNER,
}