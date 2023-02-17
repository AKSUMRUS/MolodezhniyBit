package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.remote.models.AuthInfo
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServices {

    @POST("users/login")
    fun login(
        @Body info: AuthInfo
    ) : Call<JSONObject>

    @POST("users")
    fun makeRegistration(
        @Body info: AuthInfo
    ) : Call<JSONObject>
}