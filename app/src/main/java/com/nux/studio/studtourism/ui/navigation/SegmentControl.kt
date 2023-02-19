package com.nux.studio.studtourism.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
