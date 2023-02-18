package com.nux.studio.studtourism.data.local.models

import com.squareup.moshi.Json

class DormitoryDetails(
    @Json(name = "main-info")
    val mainInfo: MainInfo?,
    val rules: Rules?,
    val services: List<Service>?,
    val documents: List<DocumentUrl>?,
    val district: String?,
    val city: String?,
)

typealias DocumentUrl = String