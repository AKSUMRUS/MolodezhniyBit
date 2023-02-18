package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1


@Composable
fun LoadingView(
    modifier: Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Body1(
                text = stringResource(R.string.loading),
                modifier = Modifier.padding(bottom = 20.dp),

                )
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary
            )
        }
    }
}