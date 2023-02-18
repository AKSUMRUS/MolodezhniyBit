package com.nux.studio.studtourism.ui.navigation

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.TopAppBarActionButton
import com.nux.studio.studtourism.ui.components.atoms.CircleAsyncImage

@Composable
fun TopBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = remember { SegmentControlTabs.values().map { it.route } }
    if (currentRoute in routes) {
        TopAppBar(
            title = {},
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("profile")
                }) {
                    CircleAsyncImage(
                        url = "https://sun1-26.userapi.com/impg/zmzFaRBkJtt_KwMGd41ARQyNMRxIctDLPD3uCg/U3HSrag1wIw.jpg?size=1035x1280&quality=95&sign=846b0408cc33466822f75ec8a3728431&type=album",
                        description = "Фото профиля"
                    )
                }
            },
            actions = {
                TopAppBarActionButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.favourites),
                    description = "Search"
                ) {

                }
                TopAppBarActionButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.notifications),
                    description = "Lock"
                ) {

                }
            }
        )
    }
}