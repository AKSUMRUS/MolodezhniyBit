package com.nux.studio.studtourism.ui.components.atoms

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.nux.studio.studtourism.R
import java.util.*

typealias Date = String;

@Composable
fun DateSelect(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val month: Int = calendar.get(Calendar.MONTH)
    val year: Int = calendar.get(Calendar.YEAR)

    calendar.time = java.util.Date()

    val datePickerDialogFrom = DatePickerDialog(
        context,
        { _: DatePicker, yearPicked: Int, monthRaw: Int, dayRaw: Int ->
            val dayPicked = String.format("%02d", dayRaw)
            val monthPicked = String.format("%02d", monthRaw + 1)
            val date = "$dayPicked.$monthPicked.$yearPicked"
            onValueChange(date)
        }, year, month, day
    )
    InputField(
        text = value,
        onValueChange = onValueChange,
        placeholder = "дд.мм.гггг",
        modifier = Modifier.then(modifier),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 15.sp,
        ),
        trailingIcon = {
            Icon(
                ImageVector.vectorResource(id = R.drawable.icon_calendar),
                tint = Color.Black,
                contentDescription = "calendar",
                modifier = Modifier
                    .clickable {
                        datePickerDialogFrom.show()
                    }
            )
        }
    )
}