package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.local.university.University
import com.nux.studio.studtourism.data.remote.models.AuthInfo
import com.nux.studio.studtourism.data.remote.models.EditUser
import com.nux.studio.studtourism.data.remote.models.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitServices {

    @POST("users/login")
    fun login(
        @Body info: AuthInfo
    ) : Call<JSONObject>

    @POST("users/signup")
    fun signUp(
        @Body info: AuthInfo
    ) : Call<JSONObject>

    @GET("me")
    fun getProfile()

    @GET("universities/all")
    fun getUniversitiesList() : Call<List<University>>

    @GET("universities/{id}")
    fun getUniversity(
        @Path("id") id: String
    ) : Call<University>

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

    @POST("users")
    fun editUser(
        @Body editUser: EditUser
    ) : Call<User>

}