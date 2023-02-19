package com.nux.studio.studtourism.data.local.models

data class Author(
    val role: String? = "Студент",
    val name: String? = "Пашка",
    val contacts: Contacts = Contacts(),
)