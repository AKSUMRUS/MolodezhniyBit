package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH4
import com.nux.studio.studtourism.ui.components.molecules.BottomSheet
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.components.molecules.CardDormitoryBooked
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun DormitoriesBookedScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading = viewModel.state.isLoading

    var height = (200..400).random()

    LaunchedEffect(true) {
        viewModel.getDormitories()
        viewModel.getDormitoriesBooked()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        BottomSheet(
            viewModel = viewModel
        ) {
//                openBottomSheet ->
            if (isLoading) {
                Text(text = "Loading...")
                LoadingViewCenter()
            } else {
                if (viewModel.state.dormitoriesBookedList.isEmpty()) {
                    HeadlineH4(text = "Нет брони")
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(top = 10.dp, start = 0.dp, end = 0.dp),
                        modifier = Modifier.background(MaterialTheme.colors.background),
                    ) {
                        itemsIndexed(viewModel.state.dormitoriesBookedList) { index, dormitoryBooked ->
                            CardDormitoryBooked(
                                dormitoryBooked = dormitoryBooked,
                                navController = navController,
                                viewModel = viewModel,
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 24.dp),
                backgroundColor = MaterialTheme.colors.surface,
                onClick = {
//                    openBottomSheet()
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filters),
                    contentDescription = "Filter's button"
                )
            }
        }
    }
}
