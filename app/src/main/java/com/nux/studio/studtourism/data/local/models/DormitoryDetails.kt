package com.nux.studio.studtourism.data.local.models

import com.squareup.moshi.Json

class DormitoryDetails(
    @Json(name = "main-info")
    val mainInfo: MainInfo?,
    val rules: Rules?,
    val service: List<Service>?,
    val documents: List<String>?,
    val district: String?,
)