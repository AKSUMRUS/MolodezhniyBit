package com.nux.studio.studtourism.data.repository

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
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val editUserFlow = _editUserFlow.asSharedFlow()

    private val _profileFlow = MutableSharedFlow<Resource<User>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val profileFlow = _profileFlow.asSharedFlow()

    suspend fun loadProfile() {
        _profileFlow.emit(Resource.Loading(true))
        val request = api.getProfile()

        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _profileFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _profileFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _profileFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _profileFlow.emit(Resource.Loading(false))
            return
        }

        _profileFlow.emit(Resource.Loading(false))
        _profileFlow.emit(Resource.Success(response))
    }

    suspend fun editUser(
        firstName: String? = null,
        lastName: String? = null,
        middleName: String? = null,
        phone: String? = null,
    ) {
        _editUserFlow.emit(Resource.Loading(true))
        val editUser = EditUser(
            id = null,
            email = null,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            gender = null,
            departureCity = null,
            phone = phone,
            socialUrl = null,
            universityName = null,
            avatar = null,
            birthday = null,
            WoS = null,
            WoS1 = null,
            studentRoleType = null
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