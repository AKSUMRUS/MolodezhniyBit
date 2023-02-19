package com.nux.studio.studtourism.data.remote.models

class EditUser (
    val id: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val gender: String?,
    val departureCity: String?,
    val phone: String?,
    val socialUrl: String?,
    val universityName: String?,
    val avatar: String?,
    val birthday: String?,
    val WoS: String?,
    val WoS1: String?,
    val studentRoleType: String?,
    val starredDormitories: Set<String>? = null
)