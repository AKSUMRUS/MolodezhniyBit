package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.data.local.models.Event
import com.nux.studio.studtourism.ui.components.atoms.*
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader
import com.nux.studio.studtourism.ui.components.molecules.BackButton
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
                Log.d("EventId", event.id)
                event.details.photos?.let {photos ->
                    if (photos.isNotEmpty()) {
                        ImagesCarousel(photos = photos)
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
                    SectionHeader(
                        text = "Описание",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(text = description, modifier = Modifier.padding(horizontal = 15.dp))
                }
                university?.details?.name?.let { name ->
                    SectionHeader(
                        text = "Организатор",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(text = name, modifier = Modifier.padding(horizontal = 15.dp))
                }
                Box(modifier = Modifier.height(120.dp))
            }
        }
        BackButton(
            navController = navController,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}
