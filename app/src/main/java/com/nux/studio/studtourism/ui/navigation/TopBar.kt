package com.nux.studio.studtourism.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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
                if (authRepository.isAuthorized()) {
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
                }
            },
            actions = {
                if (authRepository.isAuthorized()) {
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
                } else {
                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(CenterVertically)
                            .padding(end = 20.dp, bottom = 2.dp, top = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.background,
                        ),
                        shape = CircleShape,
                    ) {
                        Text(
                            text = "Войти",
                            color = MaterialTheme.colors.surface,
                        )
                    }
                }
            }
        )
    }
}