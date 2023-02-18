package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.R

@Preview
@Composable
fun SelectPreview() {
    val items = listOf("123", "one", "two", "one two")
    var selected by remember { mutableStateOf("") }
    Select(items, selected = selected, onValueChange = { selected = it })
}

@Composable
fun Select(
    items: List<String>,
    selected: String = "",
    onValueChange: (String) -> Unit
) {
    var search by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        SearchInput(text = search, onValueChange = { search = it })
        Column(modifier = Modifier.fillMaxWidth()) {
            items.forEach { item ->
                if (item.contains(search, true)) {
                    Button(onClick = { onValueChange(item) }, modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = item)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                            ) {
                                if (selected == item) {
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_check),
                                        contentDescription = "checked",
                                        modifier = Modifier
                                            .size(20.dp)
                                            .fillMaxWidth()
                                            .align(Alignment.CenterEnd)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}