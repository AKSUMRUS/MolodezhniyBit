package com.nux.studio.studtourism.data.local.models

data class Author(
    val role: String? = "Студент",
    val name: String? = "",
    val contacts: Contacts = Contacts(),
)