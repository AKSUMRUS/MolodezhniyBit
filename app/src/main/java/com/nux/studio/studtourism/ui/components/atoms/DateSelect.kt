package com.nux.studio.studtourism.ui.components.atoms

import android.app.DatePickerDialog
import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.nux.studio.studtourism.R
import java.util.*

typealias Date = String;

@Composable
fun DateSelect() {
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    var mDate by remember { mutableStateOf("") };
    val mContext = LocalContext.current
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    Button(onClick = {
        mDatePickerDialog.show()
    }, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)) {
        Text(text = mDate, color = MaterialTheme.colors.onPrimary)
    }
}