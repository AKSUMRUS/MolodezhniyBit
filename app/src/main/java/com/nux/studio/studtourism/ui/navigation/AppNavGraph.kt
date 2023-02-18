package com.nux.studio.studtourism.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nux.studio.studtourism.ui.components.screens.DormitoriesScreen
import com.nux.studio.studtourism.ui.components.screens.EventsScreen
import com.nux.studio.studtourism.ui.components.screens.LabsScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = TopTabs.DORMITORIES.route,
    ) {
        composable(TopTabs.DORMITORIES.route) {
            DormitoriesScreen()
//            Text("DORMITORIES")
        }
        composable(TopTabs.EVENTS.route) {
            EventsScreen()
//            Text("EVENTS")
        }
        composable(TopTabs.LABS.route) {
            LabsScreen()
//            Text("LABS")
        }
    }

}