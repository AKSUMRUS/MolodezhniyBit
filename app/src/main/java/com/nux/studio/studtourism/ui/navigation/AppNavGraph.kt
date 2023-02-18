package com.nux.studio.studtourism.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nux.studio.studtourism.ui.components.screens.DormitoriesScreen
import com.nux.studio.studtourism.ui.components.screens.DormitoryScreen
import com.nux.studio.studtourism.ui.components.screens.EventsScreen
import com.nux.studio.studtourism.ui.components.screens.LabsScreen
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
                viewModel = viewModel
            )
        }
        composable(SegmentControlTabs.EVENTS.route) {
            EventsScreen(
                viewModel = viewModel
            )
        }
        composable(SegmentControlTabs.LABS.route) {
            LabsScreen(
                viewModel = viewModel
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
    }

}