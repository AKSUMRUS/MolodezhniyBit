package com.nux.studio.studtourism.data.error

object ErrorCatcher {

    fun catch(code: Int): ErrorRemote {

        return when (code) {
            400 -> ErrorRemote.BadRequest
            401 -> ErrorRemote.Unauthorized
            403 -> ErrorRemote.Forbidden
            404 -> ErrorRemote.NotFound
            500 -> ErrorRemote.InternalServerError
            502 -> ErrorRemote.BadGateway
            503 -> ErrorRemote.ServiceUnavailable
            504 -> ErrorRemote.GatewayTimeout
            else -> ErrorRemote.Unknown
        }

    }

}