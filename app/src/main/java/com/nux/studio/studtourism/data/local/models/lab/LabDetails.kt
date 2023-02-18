package com.nux.studio.studtourism.data.local.models.lab

import com.nux.studio.studtourism.data.local.models.Coordinates

data class LabDetails(
    val name: String,
    val link: String?,
    val description: String?,
    val photos: List<String>?,
    val coordinates: Coordinates?,
    val owner: LabOwner?,
    val unit: LabUnit?,
    val admin: LabUnit?,
    val shortDescription: String?
)
