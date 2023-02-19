package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Dormitory

data class MapState (
    val dormitories: List<Dormitory> = listOf(),
    val error: String? = null,
    val isLoading: Boolean = false
)