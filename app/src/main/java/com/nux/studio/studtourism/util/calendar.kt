package com.nux.studio.studtourism.util

import android.content.Intent
import android.provider.CalendarContract.Events


fun buildInsertEventIntent() {
    val calIntent = Intent(Intent.ACTION_INSERT)

    calIntent.type = "vnd.android.cursor.item/event"

    calIntent.putExtra(Events.TITLE, "My House Party")

    calIntent.putExtra(Events.EVENT_LOCATION, "My Beach House")

    calIntent.putExtra(Events.DESCRIPTION, "A Pig Roast on the Beach")

//    startActivity(calIntent)

}