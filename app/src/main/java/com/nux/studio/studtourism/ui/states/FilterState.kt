package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Committee
import java.time.LocalDate

data class FilterState(
    val ratingBy: RatingBy = RatingBy.DESCENDING,
    val city: String? = "",
    val district: String? = "",
    val subject: String? = "",
    val committee: Committee? = null,
    val placeType: String? = "",
    val mealType: MealType = MealType.BREAKFAST,
    val startDate: String = "",
    val endDate: String = "",
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