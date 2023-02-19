package com.nux.studio.studtourism.ui.components.molecules

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.ui.components.atoms.DateSelect
import com.nux.studio.studtourism.ui.components.atoms.Grabber
import com.nux.studio.studtourism.ui.components.atoms.OurRadioButton
import com.nux.studio.studtourism.ui.components.atoms.Select
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader
import com.nux.studio.studtourism.ui.states.FilterState
import com.nux.studio.studtourism.ui.states.RatingBy
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomSheet(
    viewModel: MainViewModel,
    content: @Composable (() -> Unit) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var state by remember { mutableStateOf(FilterState()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state) {
//        if (state != FilterState()) {
            viewModel.updateFilters(state)
//        }
    }

    BackHandler {
        coroutineScope.launch {
            modalSheetState.hide() // trigger the LaunchedEffect
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                Grabber()
                BottomSheetHeader(
                    text = "Настройка Поиска",
                    onClose = { coroutineScope.launch { modalSheetState.hide() } }
                )
                LazyColumn(modifier = Modifier.padding(horizontal = 15.dp)) {
                    item {

                        Select(
                            viewModel.cities.toList(),
                            value = state.city ?: "",
                            placeholder = "Город",
                            onValueChange = { state = state.copy(city = it) },
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DateSelect(
                                value = state.startDate,
                                onValueChange = { state = state.copy(startDate = it) },
                                modifier = Modifier.weight(1f),
                            )
                            DateSelect(
                                value = state.endDate,
                                onValueChange = { state = state.copy(endDate = it) },
                                modifier = Modifier.weight(1f),
                            )
                        }
                        SectionHeader(text = "Рейтинг")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                        ) {
                            OurRadioButton(
                                text = "По убыванию",
                                selected = (state.ratingBy == RatingBy.DESCENDING),
                                onClick = { state = state.copy(ratingBy = RatingBy.DESCENDING) },
                                modifier = Modifier.weight(1f),
                            )
                            OurRadioButton(
                                text = "По возрастанию",
                                selected = (state.ratingBy == RatingBy.ASCENDING),
                                onClick = { state = state.copy(ratingBy = RatingBy.ASCENDING) },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
                Divider(color = MaterialTheme.colors.onBackground.copy(alpha=0.1f), thickness = 1.dp)
                Button(
                    onClick = { state = FilterState() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.onBackground
                    ),
                ) {
                    HeadlineH5(
                        text = "Сбросить фильтры",
                        modifier = Modifier.padding(20.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) {
        content {
            coroutineScope.launch {
                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
        }
    }
}

@Composable
fun BottomSheetHeader(text: String, modifier: Modifier = Modifier, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        HeadlineH5(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(40.dp),
            fontWeight = FontWeight.Bold,
        )
        IconButton(
            onClick = { onClose() },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}