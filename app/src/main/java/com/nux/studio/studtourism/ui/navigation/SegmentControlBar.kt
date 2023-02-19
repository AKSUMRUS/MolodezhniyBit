package com.nux.studio.studtourism.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun SegmentControlBar(
    navController: NavController,
    tabs: Array<SegmentControlTabs>,
    index: Int = 0
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = remember { SegmentControlTabs.values().map { it.route } }

    var defaultSelectedItemIndex by remember { mutableStateOf(index) }

    if (currentRoute in routes) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
        ) {
            SegmentedControl(
                items = tabs.toList(),
                defaultSelectedItemIndex = defaultSelectedItemIndex,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth()
                ,
                onClick = { index, tab ->
                    if (tab.route != currentRoute) {

                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    defaultSelectedItemIndex = index
                }
            )
        }
    }

}