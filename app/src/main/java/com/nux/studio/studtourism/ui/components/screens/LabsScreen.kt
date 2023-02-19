package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.molecules.CardLab
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.UniversityViewModel

@Composable
fun LabsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController,
){

    val labs = viewModel.state.labsList
    val isLoading = viewModel.state.isLoading
    val universityViewModel: UniversityViewModel = hiltViewModel()

    LaunchedEffect(true) {
        viewModel.getLabs()
    }

    Box(
        modifier = modifier
    ) {
        if (isLoading) {
            LoadingViewCenter()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
                modifier = Modifier.background(MaterialTheme.colors.background),
            ) {


                itemsIndexed(labs) { index, lab ->
                    LaunchedEffect(true) {
                        universityViewModel.loadUniversity(lab.universityId)
                    }
                    var region: String = ""
                    if (universityViewModel.state.university != null && universityViewModel.state.university!!.details != null) {
                        region = universityViewModel.state.university!!.details!!.region!!
                    }

                    CardLab(
                        lab = lab,
                        height = 250,
                        region = region,
                        onClick = {
                            navController.navigate(
                                "lab?index=$index"
                            )
                        }
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
                //
            }
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.filters),
                contentDescription = "Filter's button"
            )
        }
    }

}