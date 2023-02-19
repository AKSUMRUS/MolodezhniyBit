package com.nux.studio.studtourism.data.remote

import com.nux.studio.studtourism.data.local.models.*
import com.nux.studio.studtourism.data.local.models.lab.Lab
import com.nux.studio.studtourism.data.remote.models.AuthInfo
import com.nux.studio.studtourism.data.remote.models.EditUser
import com.nux.studio.studtourism.data.remote.models.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @POST("users/login")
    fun login(
        @Body info: AuthInfo
    ) : Call<Token>

    @POST("users/signup")
    fun signUp(
        @Body info: AuthInfo
    ) : Call<Token>

    @GET("me")
    fun getProfile(): Call<User>

    @GET("universities/all")
    fun getUniversitiesList() : Call<List<University>>

    @GET("universities/{id}")
    fun getUniversity(
        @Path("id") id: String
    ) : Call<University>

    @GET("dormitories/all")
    fun getDormitoriesList() : Call<List<Dormitory>>

    @GET("dormitories/{id}")
    fun getDormitory(
        @Path("id") id: String
    ) : Call<Dormitory>

    @GET("bookings/my")
    fun getDormitoriesBookedList() : Call<List<DormitoryBooked>>

    @GET("events/all")
    fun getEventsList() : Call<List<Event>>

    @GET("reviews")
    fun getReviews() : Call<Unit>

    @GET("labs/all")
    fun getLabsList() : Call<List<Lab>>

    @GET("labs/{id}")
    fun getLab(
        @Path("id") id: String
    ) : Call<Lab>

    @PUT("users")
    fun editUser(
        @Body editUser: EditUser
    ) : Call<User>

    @POST("bookings")
    fun makeDormitoryBooking(
        @Body booking: DormitoryBookingRequest
    ) : Call<Any>

}