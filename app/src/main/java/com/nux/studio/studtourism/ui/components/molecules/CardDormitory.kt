package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.models.getFormattedDays
import com.nux.studio.studtourism.data.local.models.getFormattedPrice
import com.nux.studio.studtourism.ui.components.atoms.Pill
import com.nux.studio.studtourism.ui.components.atoms.PillVariant
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1
import com.nux.studio.studtourism.ui.components.atoms.texts.Body2
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH2
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH4

@Composable
fun CardDormitory(
    dormitory: Dormitory,
    onClick: () -> Unit,
    height: Int,
    navController: NavController,
) {
    val price: String = getFormattedPrice(dormitory);
    val dates: String? = getFormattedDays(dormitory);

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 0.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }

    ) {
        Box(
            modifier = Modifier.height(height.dp),
//            contentAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = dormitory.details?.mainInfo?.photos?.get(0),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
//                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.9f) })
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
//                        contentAlignment = Alignment.BottomStart,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Body1(
                    text = dormitory.details?.mainInfo?.name?: "",
                    color = MaterialTheme.colors.onSecondary,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                )

                val modifierSubtitle: Modifier = Modifier.padding(start = 20.dp)


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_calendar),
                        contentDescription = "",
                        modifier = Modifier,
                        tint = MaterialTheme.colors.onSecondary,
                    )
                    if (dates != null) {
                        Body2(
                            text = dates,
                            color = MaterialTheme.colors.onSecondary,
                            modifier = modifierSubtitle,
//                            letterSpacing = -0.2040000057220459.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Normal
//                            modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon),
                        contentDescription = "",
                        modifier = Modifier,
                        tint = MaterialTheme.colors.onSecondary,
                    )

                    Body2(
                        text = price,
                        color = MaterialTheme.colors.onSecondary,
                        modifier = modifierSubtitle,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            dormitory.details?.mainInfo?.city?.let { city ->
                Pill(
                    text = city,
                    modifier = Modifier.align(Alignment.TopStart),
                    variant = PillVariant.BACKGROUND,
                )
            }
        }
    }

}