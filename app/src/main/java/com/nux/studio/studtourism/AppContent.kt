package com.nux.studio.studtourism

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.nux.studio.studtourism.ui.navigation.AppNavGraph
import com.nux.studio.studtourism.ui.navigation.SegmentControlBar
import com.nux.studio.studtourism.ui.navigation.SegmentControlTabs
import com.nux.studio.studtourism.ui.navigation.TopBar
import com.nux.studio.studtourism.ui.theme.StudTourismTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppContent() {
    StudTourismTheme {
        val tabs = remember { SegmentControlTabs.values() }
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Column {
                    TopBar(navController = navController)
                    SegmentControlBar(
                        navController = navController,
                        tabs = tabs
                    )
                }
            }
        ) {
            AppNavGraph(
                navController = navController,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}