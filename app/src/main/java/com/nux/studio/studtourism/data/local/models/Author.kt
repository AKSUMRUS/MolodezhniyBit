package com.nux.studio.studtourism.data.local.models

data class Author(
    val role: String? = null,
    val name: String? = null,
    val contacts: Contacts = Contacts(),
)