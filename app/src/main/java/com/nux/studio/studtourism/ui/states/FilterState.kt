package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Committee

data class FilterState(
    var ratingBy: RatingBy = RatingBy.DESCENDING,
    var city: String? = null,
    var district: String? = null,
    var subject: String? = null,
    var committee: Committee? = null,
    var placeType: String? = null,
    var mealType: MealType = MealType.BREAKFAST
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