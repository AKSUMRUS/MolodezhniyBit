package com.nux.studio.studtourism.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TopBar(
    navController: NavController,
    tabs: Array<TopTabs>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = remember { TopTabs.values().map { it.route } }

    if (currentRoute in routes) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SegmentedControl(
                items = tabs.toList(),
                modifier = Modifier
                    .align(CenterHorizontally),
                onClick = { tab ->
                    if (tab.route != currentRoute) {
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }

}