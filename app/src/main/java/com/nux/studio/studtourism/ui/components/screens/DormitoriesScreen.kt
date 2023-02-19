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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.ui.components.molecules.BottomSheet
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.components.molecules.SegmentControlMap
import com.nux.studio.studtourism.ui.states.FilterState
import com.nux.studio.studtourism.ui.states.SortOrder
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import kotlin.math.pow

@Composable
fun DormitoriesScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading = viewModel.state.isLoading

    val indexView = viewModel.state.indexView

    val filters = viewModel.filterState

    val starredDormitories = viewModel.state.starredDormitories

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
                val dormitories = filterDormitories(viewModel.state.dormitoriesList, filters);
                if (indexView == 0) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(top = 10.dp, start = 0.dp, end = 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background),
                    ) {
                        Log.d("Dormitories", filters.toString())
                        var height = 300
                        itemsIndexed(dormitories) { index, dormitory ->
                            if (index % 2 == 0) {
                                height =
                                    ((-1.0).pow((index / 2).toDouble()) * ((index * 30) % 100) + 300).toInt()
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
                                onFavouriteClick = viewModel::favouriteDormitory,
                                isStarred = starredDormitories.contains(dormitory.id)
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
                            navController = navController,
                            dormitories = dormitories
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

private fun getLongFromDateStr(dateStr: String): Long {
    val formatter = DateTimeFormatterBuilder()
        .appendPattern("dd.MM.yyyy")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
        .toFormatter()
    val date: LocalDateTime =
        LocalDateTime.parse(dateStr, formatter)
    val millis: Long =
        date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
    Log.d("Mills", millis.toString())
    return millis;
}

private fun checkDates(filters: FilterState, dormitory: Dormitory): Boolean {
    if (filters.startDate.isNotEmpty() && filters.endDate.isNotEmpty()) {
        if (dormitory.rooms == null) {
            return false;
        }
        val stDate = getLongFromDateStr(filters.startDate);
        val enDate = getLongFromDateStr(filters.endDate);
        return dormitory.rooms.values.any { room ->
            ((room.details?.dateRange?.from ?: Long.MAX_VALUE) <= stDate)
                    && (enDate <= (room.details?.dateRange?.to ?: 0))
        }
    }
    return true;
}

private fun checkDormitory(
    dormitory: Dormitory,
    filters: FilterState
): Boolean {
    if (!checkDates(filters, dormitory)) {
        return false
    }
    if (dormitory.details?.mainInfo?.name.isNullOrEmpty()) {
        return false
    }
    if (dormitory.onModeration == true) {
        return false
    }
    if (!filters.city.isNullOrEmpty()) {
        if (dormitory.details?.mainInfo?.city?.lowercase()
                ?.contains(filters.city.toString().lowercase()) != true
        ) {
            return false
        }
    }
    return true
}

private fun filterDormitories(
    dormitories: List<Dormitory>,
    filters: FilterState
): List<Dormitory> {
    val filteredDormitories = dormitories.filter { item -> checkDormitory(item, filters) }
    if (filters.nameBy != SortOrder.NONE) {
        val selector = { dorm: Dormitory -> dorm.details?.mainInfo?.name }
        if (filters.nameBy == SortOrder.ASCENDING) {
            return filteredDormitories.sortedBy(selector)
        } else {
            return filteredDormitories.sortedByDescending(selector)
        }
    }
    return filteredDormitories;
}

@Composable
private fun GoogleMapView(
    viewModel: MainViewModel,
    navController: NavController,
    dormitories: List<Dormitory>
) {
    val markers = dormitories
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
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        )
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
