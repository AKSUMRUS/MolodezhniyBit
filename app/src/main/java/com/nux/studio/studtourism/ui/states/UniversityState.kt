package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.University

data class UniversityState (
    val university: University? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)