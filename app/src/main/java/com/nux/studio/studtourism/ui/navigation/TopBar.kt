package com.nux.studio.studtourism.ui.navigation

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.TopAppBarActionButton
import com.nux.studio.studtourism.data.repository.AuthRepository
import com.nux.studio.studtourism.ui.components.atoms.CircleAsyncImage
import com.nux.studio.studtourism.ui.viewmodels.AuthViewModel
import javax.inject.Inject



@Composable
fun TopBar(
    navController: NavController,
) {

    val authRepository = hiltViewModel<AuthViewModel>().repository

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = remember { SegmentControlTabs.values().map { it.route } }
    if (currentRoute in routes) {
        TopAppBar(
            title = {},
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = {
                    if(authRepository.isAuthorized()) {
                        navController.navigate("profile") {
                            launchSingleTop = false
                            restoreState = true
                        }
                    } else {
                        navController.navigate("login")
                    }
                }) {
                    CircleAsyncImage(
                        url = "https://sun9-43.userapi.com/impg/f-fGHeXhvKyKaKAMzV7lcGQWnQULXPSrT-eMBQ/No-X0HpAlmM.jpg?size=1441x2160&quality=95&sign=c1b60c1cc758e15c8b85a2d583331204&type=album",
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