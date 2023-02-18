package com.nux.studio.studtourism.ui.components.molecules

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.states.FilterState
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
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )

    var state by remember { mutableStateOf(FilterState()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state) {
        if(state != FilterState()) {
            viewModel.updateFilters(state)
        }
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
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
                
                InputField(
                    text = state.city?: "",
                    onValueChange = {
                        state = state.copy(city = it)
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()})
                )
            }
        }
    ) {
        content {
            coroutineScope.launch {
                if (modalSheetState.isVisible) {
                    modalSheetState.hide()
                }
                else {
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    }

}