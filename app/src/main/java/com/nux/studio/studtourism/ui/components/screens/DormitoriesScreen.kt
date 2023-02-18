package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.molecules.BottomSheet
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.components.molecules.SegmentControlMap
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun DormitoriesScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading = viewModel.state.isLoading

    var height = (200..400).random()

    var indexView by remember { mutableStateOf(0) }

    val filters = viewModel.filterState

    LaunchedEffect(true) {
        viewModel.getDormitories()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        BottomSheet(
            viewModel = viewModel,
        ) { openBottomSheet ->
            if (isLoading) {
                LoadingViewCenter()
            } else {
                if(indexView == 0) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(top = 10.dp, start = 0.dp, end = 0.dp),
                        modifier = Modifier.background(MaterialTheme.colors.background),
                    ) {
                        Log.d("Dormitories", filters.toString())
                        val list = viewModel.state.dormitoriesList.filter { item ->
                            var can = true
                            if (!filters.city.isNullOrEmpty()) {
                                can =
                                    can && (item.details?.mainInfo?.city?.contains(filters.city.toString())
                                        ?: true)
                            }
                            can
                        }
                        itemsIndexed(list) { index, dormitory ->
                            if (index % 2 == 0) {
                                height = (200..400).random()
                            }

                            CardDormitory(
                                dormitory = dormitory,
                                onClick = {
                                    navController.navigate(
                                        "dormitory?index=${dormitory.id}"
                                    )
                                },
                                height = height,
                                navController = navController,
                            )
                        }
                    }
                }
                else if(indexView == 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ) {
                        Text("map")
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 24.dp),
                backgroundColor = MaterialTheme.colors.surface,
                onClick = {
                    Log.e("AAAAAHK", "HERE")
                    openBottomSheet()
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filters),
                    contentDescription = "Filter's button"
                )
            }

            SegmentControlMap(
                items = listOf("Лента","Карта"),
                defaultSelectedItemIndex = indexView,
                onItemSelection = { index ->
                    indexView = index
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            )
        }
    }
}
