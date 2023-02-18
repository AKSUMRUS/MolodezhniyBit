package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.prefs.TokenPrefs
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.AuthInfo
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import retrofit2.awaitResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val tokenPrefs: TokenPrefs,
    private val api: RetrofitServices,
    private val profileRepository: ProfileRepository,
) {

    private val _loginFlow = MutableSharedFlow<Resource<Unit>>()
    val loginFlow = _loginFlow.asSharedFlow()

    private val _signUpFlow = MutableSharedFlow<Resource<Unit>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val editUserFlow = profileRepository.editUserFlow

    fun getToken(): String? {
        return tokenPrefs.token
    }

    suspend fun login(
        email: String,
        password: String
    ) {
        _loginFlow.emit(Resource.Loading(true))

        val request = api.login(AuthInfo(email = email, password = password))

        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()?.getString("token")
            } else {
                _loginFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _loginFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _loginFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _loginFlow.emit(Resource.Loading(false))
            return
        }

        tokenPrefs.token = response

        _loginFlow.emit(Resource.Success(Unit))

        _loginFlow.emit(Resource.Loading(false))
    }

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        middleName: String,
        phone: String,
    ) {
        _signUpFlow.emit(Resource.Loading(true))

        val authInfo = AuthInfo(email = email, password = password)
        val request = api.signUp(authInfo)

        val response = try {
            val responseApi = request.awaitResponse()
            when {
                responseApi.code() == 200 -> responseApi.body()
                else -> {
                    _signUpFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                    _signUpFlow.emit(Resource.Loading(false))
                    return
                }
            }
        } catch (e: Exception) {
            _signUpFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _signUpFlow.emit(Resource.Loading(false))
            return
        }

        tokenPrefs.token = response?.token
        profileRepository.editUser(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            phone = phone,
        )
        _signUpFlow.emit(Resource.Success(Unit))
        _signUpFlow.emit(Resource.Loading(false))
    }
}