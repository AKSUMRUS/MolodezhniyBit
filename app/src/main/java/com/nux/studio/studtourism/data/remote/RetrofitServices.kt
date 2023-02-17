package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.remote.models.LoginInfo
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitServices {

    @POST("users/login")
    fun login(
        @Body info: LoginInfo
    ) : Call<JSONObject>

    @GET("me")
    fun getProgile()

    @GET("universities/all")
    fun getUniversitiesList()

    @GET("universities/{id}")
    fun getUniversity(
        @Path("id") id: String
    )

    @GET("dormitories/all")
    fun getDormitoriesList()

    @GET("dormitories/{id}")
    fun getDormitory(
        @Path("id") id: String
    )

    @GET("events/all")
    fun getEventsList()

    @GET("reviews")
    fun getReviews()

    @GET("labs/all")
    fun getLabsList()

    @GET("labs/{id}")
    fun getLab(
        @Path("id") id: String
    )

}