package com.nux.studio.studtourism.ui.navigation

import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nux.studio.studtourism.R

/**
 * items : list of items to be render
 * defaultSelectedItemIndex : to highlight item by default (Optional)
 * useFixedWidth : set true if you want to set fix width to item (Optional)
 * itemWidth : Provide item width if useFixedWidth is set to true (Optional)
 * cornerRadius : To make control as rounded (Optional)
 * color : Set color to control (Optional)
 * onItemSelection : Get selected item index
 */
@Composable
fun SegmentedControl(
    items: List<SegmentControlTabs>,
    modifier: Modifier = Modifier,
    defaultSelectedItemIndex: Int = 0,
    onClick: (selectedItem: SegmentControlTabs) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier)
    ) {
        items.forEachIndexed { index, item ->
            TextButton(
                onClick = {
                    selectedIndex.value = index
                    onClick(item)
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = item.title),
                    fontWeight = FontWeight.Normal,
                    color = if (selectedIndex.value == index) {
                        MaterialTheme.colors.onBackground
                    } else {
                        MaterialTheme.colors.onBackground.copy(alpha = 0.6f)
                    },
                )
            }
        }
    }
}
