package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Committee

data class FilterState(
    val ratingBy: RatingBy = RatingBy.DESCENDING,
    val city: String? = null,
    val district: String? = null,
    val subject: String? = null,
    val committee: Committee? = null,
    val placeType: String? = null,
    val mealType: MealType = MealType.BREAKFAST
)

enum class RatingBy{
    DESCENDING,
    ASCENDING,
}

enum class MealType {
    BREAKFAST,
    DINNER,
    BREAKFAST_AND_DINNER,
}