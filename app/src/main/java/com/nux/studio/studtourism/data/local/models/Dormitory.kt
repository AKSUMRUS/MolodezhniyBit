package com.nux.studio.studtourism.data.local.models

data class Dormitory (
    val userId: String?,
    val universityId: String?,
    val createdTimestamp: Long?,
    val details: DormitoryDetails?,
    val rooms: Map<String, Room>?,
    val onModeration: Boolean?,
    val id: String,
    val timestamp: Long?,
    val updatedTimestamp: Long?,
)

fun getFormattedPrice(dormitory: Dormitory): String {
    var minPrice: Int? = null
    var maxPrice: Int? = null
    dormitory.rooms?.forEach { entry ->
        val price = entry.value.details?.price?.toIntOrNull()
        if (price != null) {
            if (minPrice == null || price < minPrice!!) {
                minPrice = price
            }
            if (maxPrice == null || price > maxPrice!!) {
                maxPrice = price
            }
        }
    }
    return if (minPrice != null && maxPrice != null) {
        if (minPrice == maxPrice) {
            "$minPrice₽"
        } else {
            "$minPrice₽ – $maxPrice₽"
        }
    } else {
        "Цена не указана"
    }
}

fun getFormattedDays(dormitory: Dormitory): String? {
    val minDays = dormitory.details?.mainInfo?.minDays
    val maxDays = dormitory.details?.mainInfo?.maxDays
    if (!minDays.isNullOrEmpty() && !maxDays.isNullOrEmpty()) {
        if (minDays == maxDays) {
            return "$minDays дней"
        }
        return "$minDays – $maxDays дней"
    } else {
        return null
    }
}

fun getFormattedAddress(dormitory: Dormitory): String {
    val address_parts = mutableListOf<String?>()
    val city = dormitory.details?.mainInfo?.city
    if (!city.isNullOrEmpty()) {
        address_parts.add(city)
    }
    val street = dormitory.details?.mainInfo?.street
    if (!street.isNullOrEmpty()) {
        address_parts.add(street)
    }
    val house = dormitory.details?.mainInfo?.houseNumber
    if (!house.isNullOrEmpty()) {
        address_parts.add(house)
    }
    val address = address_parts.joinToString(", ")
    return if (address.isNotEmpty()) {
        address
    } else {
        "Адрес не указан"
    }
}