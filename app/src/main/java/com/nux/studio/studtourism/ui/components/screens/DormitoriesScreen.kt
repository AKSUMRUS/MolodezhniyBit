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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.Coordinates
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

    var indexView = viewModel.state.indexView

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
                if (indexView == 0) {
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
                } else if (indexView == 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ) {
                        GoogleMapView(
                            viewModel = viewModel,
                            navController = navController
                        )
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
                items = listOf("Лента", "Карта"),
                defaultSelectedItemIndex = indexView,
                onItemSelection = { index ->
                    viewModel.setIndexView(index)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun GoogleMapView(
    viewModel: MainViewModel,
    navController: NavController,
) {
    val markers = viewModel.state.dormitoriesList
        .mapNotNull { dormitory ->
            val latitude = dormitory.details?.mainInfo?.coordinates?.lat?.toDouble()
            val longitude = dormitory.details?.mainInfo?.coordinates?.lng?.toDouble()

            if (latitude == null || longitude == null) {
                return@mapNotNull null
            }

            val coordinates = LatLng(latitude, longitude)

            MarkerInfo(
                id = dormitory.id,
                state = MarkerState(coordinates),
                title = dormitory.details.mainInfo.name,
                snippet = dormitory.details.district,
            )
        }

    var latitudeSum = 0.0
    var longitudeSum = 0.0
    val size = markers.size
    markers.forEach { marketInfo ->
        latitudeSum += marketInfo.state.position.latitude
        longitudeSum += marketInfo.state.position.longitude
    }

    val center = LatLng(latitudeSum / size, longitudeSum / size)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 3.5f)
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        markers.forEach { markerInfo ->
            Marker(
                state = markerInfo.state,
                title = markerInfo.title,
                snippet = markerInfo.snippet,
                onClick = {
                    navController.navigate(
                        "dormitory?index=${markerInfo.id}"
                    )
                    true
                }
            )
        }
    }
}


private data class MarkerInfo(
    val id: String,
    val state: MarkerState,
    val title: String?,
    val snippet: String?,
)

