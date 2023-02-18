package com.nux.studio.studtourism.ui.navigation

import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH4
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH6

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
    onClick: (index: Int, selectedItem: SegmentControlTabs) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(defaultSelectedItemIndex) }

    Row(
        modifier = Modifier
            .padding(16.dp, bottom = 2.dp)
            .then(modifier)
    ) {
        items.forEachIndexed { index, item ->
            TextButton(
                onClick = {
                    selectedIndex = index
                    onClick(index, item)
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                HeadlineH6(
                    text = stringResource(id = item.title),
                    fontWeight = FontWeight.Normal,
                    color = if (selectedIndex == index) {
                        MaterialTheme.colors.onSurface
                    } else {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    },
                )
            }
        }
    }
}
