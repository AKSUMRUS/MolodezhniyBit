package com.nux.studio.studtourism.ui.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nux.studio.studtourism.ui.components.screens.*
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val viewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = SegmentControlTabs.DORMITORIES.route,
    ) {
        composable(SegmentControlTabs.DORMITORIES.route) {
            DormitoriesScreen(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            )
        }
        composable(SegmentControlTabs.EVENTS.route) {
            EventsScreen(
                viewModel = viewModel,
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                navController = navController,
            )
        }
        composable(SegmentControlTabs.LABS.route) {
            LabsScreen(
                viewModel = viewModel,
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                navController = navController,
            )
        }
        composable(
            route = "dormitory?index={index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){ backStackEntry ->
            Log.e("LOGGGG", backStackEntry.arguments.toString())
            val index = backStackEntry.arguments?.getInt("index", 0)?: 0
            Log.e("LOGGGG", index.toString())
            DormitoryScreen(
                index = index,
                viewModel = viewModel
            )
        }

        composable("profile") {
            ProfileScreen()
        }
    }

}