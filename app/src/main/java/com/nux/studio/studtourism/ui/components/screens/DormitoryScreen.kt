package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.ledokol.dvor_app.ui.components.atoms.texts.HeadlineH3
import com.ledokol.dvor_app.ui.components.atoms.texts.HeadlineH4
import com.nux.studio.studtourism.R

@Preview
@Composable
fun DormitoryScreenPreview() {
    DormitoryScreen()
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
        Text(
            text = "Комната 1",
            fontSize = 20.sp,
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
        Text(
            text = "Комнаты",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
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
        Text(
            text = "Услуга 1",
            fontSize = 20.sp,
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
) {
    val name = "Dormitory 123"
    val photo =
        "https://stud-files.sabir.pro/files/PtA4pFzxry-e6e200f4363190c4400ba0dba4958a16b719f4e33bc5e14f50e4ff6fdf8b871c.jpg"
    val price = "1000 рублей"
    val minDays = "2"
    val maxDays = "25"
    LazyColumn() {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = photo,
                    contentDescription = "Фото общежития",
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(15.dp, 10.dp),
                        text = "Тольятти",
                        fontSize = 20.sp
                    )
                }
            }
            HeadlineH3(
                text = name, modifier = Modifier.padding(15.dp)
            )
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
                    text = "$minDays - $maxDays дней",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                )
            }
            Rooms()
            Services()
        }
    }

}