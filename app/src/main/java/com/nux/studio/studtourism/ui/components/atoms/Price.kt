package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.Service
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.theme.LightBlue

@Composable
fun Price(
    price: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_wallet),
            contentDescription = "Price",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically),
            tint = color,
        )
        Text(
            text = price,
            fontSize = 20.sp,
            modifier = Modifier.padding(15.dp),
            color = color,
        )
    }
}

fun formatPrice(price: String?) : String {
    return price?.plus("₽") ?: "Цена не указана";
}