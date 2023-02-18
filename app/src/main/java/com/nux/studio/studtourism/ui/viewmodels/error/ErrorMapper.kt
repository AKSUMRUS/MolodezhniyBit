package com.nux.studio.studtourism.ui.viewmodels.error

import android.content.Context
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.error.ErrorRemote

class ErrorMapper(
    private val context: Context
) {

    fun map(error: ErrorRemote?): String? {

        val message: String? = when (error) {
            is ErrorRemote.Unauthorized -> {
                context.getString(R.string.error_unauthorized)
            }
            is ErrorRemote.Forbidden -> {
                context.getString(R.string.error_forbidden)
            }
            is ErrorRemote.NotFound -> {
                context.getString(R.string.error_not_found)
            }
            is ErrorRemote.NoInternet -> {
                null
            }
            is ErrorRemote.BadRequest -> {
                null
            }
            is ErrorRemote.InternalServerError -> {
                null
            }
            is ErrorRemote.BadGateway -> {
                null
            }
            is ErrorRemote.ServiceUnavailable -> {
                null
            }
            is ErrorRemote.GatewayTimeout -> {
                null
            }
            is ErrorRemote.Unknown -> {
                null
            }
            else -> {
                error.toString()
            }
        }

        return message
    }

}