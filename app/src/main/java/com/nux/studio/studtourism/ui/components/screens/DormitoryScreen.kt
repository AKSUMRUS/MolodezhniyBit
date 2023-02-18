package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH3
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH4
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.models.DormitoryDetails
import com.nux.studio.studtourism.data.local.models.MainInfo
import com.nux.studio.studtourism.data.local.models.University
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.Pill
import com.nux.studio.studtourism.ui.components.atoms.PillVariant
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader

@Preview
@Composable
fun DormitoryScreenPreview() {
//    DormitoryScreen(
//    )
}

@Composable
fun Room(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        HeadlineH5(
            text = "Комната 1",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )
        Text(
            text = "1000 рублей", fontSize = 15.sp, modifier = Modifier.padding(15.dp)
        )
        Text(
            text = "Описание", fontSize = 15.sp, modifier = Modifier.padding(15.dp)
        )
    }
}

@Composable
fun Rooms(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        HeadlineH4(
            text = "Комнаты",
            modifier = Modifier.padding(15.dp)
        )
        Room()
        Room()
    }
}

@Composable
fun Service(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        HeadlineH5(
            text = "Услуга 1",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )
        Text(
            text = "1000 рублей", fontSize = 15.sp, modifier = Modifier.padding(15.dp)
        )
        Text(
            text = "Описание", fontSize = 15.sp, modifier = Modifier.padding(15.dp)
        )
    }
}

@Composable
fun Services(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
    ) {
        HeadlineH4(text = "Услуги", modifier = Modifier.padding(15.dp))
        Service()
        Service()
    }
}

@Composable
fun DormitoryScreen(
    dormitory: Dormitory
) {
    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.primary)) {
        item {
            LazyRow() {
                item {
                    dormitory.details?.mainInfo?.photos?.forEach { photoUrl ->
                        AsyncImage(
                            model = photoUrl,
                            contentDescription = "Фото общежития",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                        )
                    }
                }
            }
            HeadlineH3(
                text = dormitory.details?.mainInfo!!.name, modifier = Modifier.padding(3.dp, 15.dp)
            )
            Row() {
                dormitory.details.mainInfo.city?.let { city ->
                    Pill(dormitory.details.mainInfo.city, PillVariant.CYAN_OUTLINE)
                }
            }
            val minDays = dormitory.details.mainInfo.minDays
            val maxDays = dormitory.details.mainInfo.maxDays
            if (!minDays.isNullOrEmpty() && !maxDays.isNullOrEmpty()) {
                Row(
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_calendar),
                        contentDescription = "Dates",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "$minDays – $maxDays дней",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(15.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            var minPrice: Int? = null;
            var maxPrice: Int? = null;
            dormitory.rooms?.forEach { entry ->
                val price = entry.value.details?.price?.toIntOrNull();
                if (price != null) {
                    if (minPrice == null || price < minPrice!!) {
                        minPrice = price
                    }
                    if (maxPrice == null || price > maxPrice!!) {
                        maxPrice = price
                    }
                }
            }
            val price = if (minPrice != null && maxPrice != null) {
                "$minPrice₽ – $maxPrice₽"
            } else {
                "Цена не указана"
            }
            Row(
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_wallet),
                    contentDescription = "Price",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = price, fontSize = 20.sp, modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                )
            }
            GetAddresss(dormitory).let { address ->
                SectionHeader(text = "Адрес")
                Text(text = address ?: "Адрес не указан", modifier = Modifier.padding(15.dp))
            }
            ButtonPrimary(text = "Забронировать") {}
            Rooms()
            Services()
        }
    }
}

fun GetAddresss(dormitory: Dormitory): String? {
    val address_parts = mutableListOf<String?>()
    val city = dormitory.details?.mainInfo?.city;
    if (!city.isNullOrEmpty()) {
        address_parts.add(city);
    }
    val street = dormitory.details?.mainInfo?.street;
    if (!street.isNullOrEmpty()) {
        address_parts.add(street);
    }
    val house = dormitory.details?.mainInfo?.houseNumber;
    if (!house.isNullOrEmpty()) {
        address_parts.add(house);
    }
    val address = address_parts.joinToString(", ")
    return if (address.isNotEmpty()) {
        address
    } else {
        null
    }
}