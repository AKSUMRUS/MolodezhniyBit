package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.lab.Lab
import com.nux.studio.studtourism.ui.components.atoms.Mail
import com.nux.studio.studtourism.ui.components.atoms.Phone
import com.nux.studio.studtourism.ui.components.atoms.Pill
import com.nux.studio.studtourism.ui.components.atoms.PillVariant
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.UniversityViewModel

@Composable
fun LabScreen(
    index: Int,
    viewModel: MainViewModel,
    navController: NavController
) {
    val lab: Lab = viewModel.state.labsList[index]

    val universityViewModel: UniversityViewModel = hiltViewModel()
    LaunchedEffect(true) {
        universityViewModel.loadUniversity(lab.universityId)
    }
    val university = universityViewModel.state.university

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            item {
                lab.details.photos?.let {photos ->
                    if (photos.isNotEmpty()) {
                        ImagesCarousel(photos = photos)
                    }
                }
                lab.details.name?.let { name ->
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
                    lab.details?.city?.let { region ->
                        Pill(region, variant = PillVariant.BACKGROUND)
                    }
                }
                lab.details.description?.let { description ->
                    SectionHeader(
                        text = "Описание",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(text = description, modifier = Modifier.padding(horizontal = 15.dp))
                }
                university?.details?.name?.let { name ->
                    SectionHeader(
                        text = "Организация",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(text = name, modifier = Modifier.padding(horizontal = 15.dp))
                }
                lab.details.unit?.let { unit ->
                    SectionHeader(
                        text = "Ответсвенное лицо",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    unit.name?.let { name ->
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .padding(bottom = 15.dp)
                        )
                    }
                    Column(Modifier.padding(horizontal = 15.dp)) {
                        unit.phone?.let { phone ->
                            Phone(phone, Modifier.padding(bottom = 15.dp))
                        }
                        unit.email?.let { email ->
                            Mail(email)
                        }
                    }
                }
                Box(modifier = Modifier.height(20.dp))
            }
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
