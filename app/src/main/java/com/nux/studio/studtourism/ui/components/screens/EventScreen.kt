package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.Event
import com.nux.studio.studtourism.ui.components.atoms.*
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader
import com.nux.studio.studtourism.ui.components.molecules.convertLongToTime
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.UniversityViewModel

@Composable
fun EventScreen(
    index: Int,
    viewModel: MainViewModel,
    navController: NavController
) {
    val event: Event = viewModel.state.eventsList[index]

    val universityViewModel: UniversityViewModel = hiltViewModel()

    LaunchedEffect(true) {
        universityViewModel.loadUniversity(event.universityId)
    }
    val university = universityViewModel.state.university
    val dates: String =
        "${convertLongToTime(event.details.dates.from)} - ${convertLongToTime(event.details.dates.to)}"

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            item {
                LazyRow() {
                    item {
                        event.details?.photos?.forEach { photoUrl ->
                            AsyncImage(
                                model = photoUrl,
                                contentDescription = "Фото события",
                                modifier = Modifier
                                    .width(LocalConfiguration.current.screenWidthDp.dp)
                                    .padding(0.dp),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }
                }
                event.details.name?.let { name ->
                    HeadlineH5(
                        text = name,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .padding(top = 20.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .background(Color.Transparent)
                ) {
                    university?.details?.region?.let { region ->
                        Pill(region, variant = PillVariant.BACKGROUND)
                    }
                    university?.details?.district?.let { region ->
                        Pill(region, variant = PillVariant.BACKGROUND)
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth(),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Dates(dates, modifier = Modifier.padding(bottom = 7.dp))
                        Price(formatPrice(event.details.price))
                    }
                }
                event.details.description?.let { description ->
                    SectionHeader(text = "Описание", modifier = Modifier.padding(horizontal = 15.dp))
                    Text(text = description, modifier = Modifier.padding(horizontal = 15.dp))
                }
                university?.details?.name?.let {name ->
                    SectionHeader(text = "Организатор", modifier = Modifier.padding(horizontal = 15.dp))
                    Text(text = name, modifier = Modifier.padding(horizontal = 15.dp))
                }
                Box(modifier = Modifier.height(120.dp))
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(horizontal = 25.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
            ),
            onClick = { /*TODO*/ },
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "Оставить заявку",
                color = MaterialTheme.colors.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .padding(25.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack()
                }
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = "go back",
                tint = Color.White,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        end = 12.dp
                    )
            )
            Text(
                text = "Назад",
                color = Color.White
            )
        }
    }
}
