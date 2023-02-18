package com.nux.studio.studtourism.data.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.EditUser
import com.nux.studio.studtourism.data.remote.models.User
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.awaitResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: RetrofitServices
) {

    private val _editUserFlow = MutableSharedFlow<Resource<User>>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val editUserFlow = _editUserFlow.asSharedFlow()

    private val _profileFlow = MutableSharedFlow<Resource<User>>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val profileFlow = _profileFlow.asSharedFlow()

    suspend fun loadProfile() {
        _profileFlow.emit(Resource.Loading(true))
        val request = api.getProfile()

        val response = try {
            val responseApi = request.awaitResponse()
            Log.d("RRR", "$responseApi")
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _profileFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _profileFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            Log.d("RRR", "$e")
            _profileFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _profileFlow.emit(Resource.Loading(false))
            return
        }

        _profileFlow.emit(Resource.Success(response))
        _profileFlow.emit(Resource.Loading(false))
    }

    suspend fun editUser(
        id: String? = null,
        email: String? = null,
        gender: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        middleName: String? = null,
        phone: String? = null,
        departureCity: String? = null,
        socialUrl: String? = null,
        universityName: String? = null,
        avatar: String? = null,
        birthday: String? = null,
        WoS: String? = null,
        WoS1: String? = null,
        studentRoleType: String? = null,
    ) {
        _editUserFlow.emit(Resource.Loading(true))
        val editUser = EditUser(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            gender = gender,
            departureCity = departureCity,
            phone = phone,
            socialUrl = socialUrl,
            universityName = universityName,
            avatar = avatar,
            birthday = birthday,
            WoS = WoS,
            WoS1 = WoS1,
            studentRoleType = studentRoleType
        )

        val request = api.editUser(editUser)

        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _editUserFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _editUserFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _editUserFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _editUserFlow.emit(Resource.Loading(false))
            return
        }

        _editUserFlow.emit(Resource.Loading(false))
        _editUserFlow.emit(Resource.Success(response))
    }

}