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
import com.nux.studio.studtourism.ui.components.atoms.CircleAsyncImage
import com.nux.studio.studtourism.ui.viewmodels.AuthViewModel
import com.nux.studio.studtourism.ui.viewmodels.ProfileViewModel

@Composable
fun TopBar(
    navController: NavController,
) {

    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val authRepository = hiltViewModel<AuthViewModel>().repository

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val photoUrl = profileViewModel.getProfileUrl()

    val routes = remember { SegmentControlTabs.values().map { it.route } }
    if (currentRoute in routes) {
        TopAppBar(
            title = {},
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = {
                    if (authRepository.isAuthorized()) {
                        navController.navigate("profile") {
                            launchSingleTop = false
                            restoreState = true
                        }
                    } else {
                        navController.navigate("login?to=profile")
                    }
                }) {
                    CircleAsyncImage(
                        url = photoUrl,
                        description = "Фото профиля"
                    )
                }
            },
            actions = {
                TopAppBarActionButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_favourite_not_selected),
                    description = "Search"
                ) {

                }
                TopAppBarActionButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_calendar_checked),
                    description = "Booking",
                    onClick = {
                        if (authRepository.isAuthorized()) {
                            navController.navigate("booking/dormitories") {
                            }
                        } else {
                            navController.navigate("login?to=booking/dormitories")
                        }
                    }
                )
                TopAppBarActionButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.notifications),
                    description = "Lock"
                ) {

                }
            }
        )
    }
}