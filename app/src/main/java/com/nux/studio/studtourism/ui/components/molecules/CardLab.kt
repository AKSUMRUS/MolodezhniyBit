@file:Suppress("UNNECESSARY_SAFE_CALL")

package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nux.studio.studtourism.data.local.models.lab.Lab
import com.nux.studio.studtourism.ui.components.atoms.Pill
import com.nux.studio.studtourism.ui.components.atoms.PillVariant
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1

@Composable
fun CardLab(
    lab: Lab,
    onClick: () -> Unit,
    region: String,
    height: Int,
) {

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
                model = lab.details.photos?.get(0),
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
                if(lab.details.name!=null){
                    Body1(
                        text = lab.details.name,
                        color = MaterialTheme.colors.onSecondary,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Pill(
                text = region,
                modifier = Modifier.align(Alignment.TopStart),
                variant = PillVariant.BACKGROUND,
            )
        }
    }

}