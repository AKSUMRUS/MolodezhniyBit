package com.nux.studio.studtourism.ui.navigation

import androidx.annotation.StringRes
import com.nux.studio.studtourism.R

enum class TopTabs(
    @StringRes val title: Int,
    val route: String,
) {
    DORMITORIES(
        title = R.string.navigation_dormitories,
        route = "dormitories"
    ),
    EVENTS(
        title = R.string.navigation_events,
        route = "events"
    ),
    LABS(
        title = R.string.navigation_labs,
        route = "labs"
    ),

}