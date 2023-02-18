package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.remote.models.User

data class ProfileState (
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)