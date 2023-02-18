package com.nux.studio.studtourism.ui.components.atoms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.R

@Composable
fun IconWithInfo(
    @DrawableRes id: Int,
    contentDescription: String?,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Image(
            imageVector = ImageVector.vectorResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.padding(15.dp),
        )
    }
}


@Composable
fun Price(
    price: String,
    modifier: Modifier = Modifier,
) {
    IconWithInfo(
        id = R.drawable.icon_wallet,
        contentDescription = "Price",
        text = price,
        modifier = modifier
    )
}

fun formatPrice(price: String?): String {
    return price?.plus("₽") ?: "Цена не указана";
}

@Composable
fun Dates(
    dates: String,
    modifier: Modifier = Modifier,
) {
    IconWithInfo(
        id = R.drawable.icon_calendar,
        contentDescription = "Dates",
        text = dates,
        modifier = modifier
    )
}

@Composable
fun Phone(
    phone: String,
    modifier: Modifier = Modifier,
) {
    IconWithInfo(
        id = R.drawable.icon_phone,
        contentDescription = "phone",
        text = phone,
        modifier = modifier
    )
}
@Composable
fun Mail(
    mail: String,
    modifier: Modifier = Modifier,
) {
    IconWithInfo(
        id = R.drawable.icon_mail,
        contentDescription = "mail",
        text = mail,
        modifier = modifier
    )
}