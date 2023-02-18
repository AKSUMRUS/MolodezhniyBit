package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.prefs.TokenPrefs
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.AuthInfo
import com.nux.studio.studtourism.data.remote.models.EditUser
import com.nux.studio.studtourism.data.remote.models.User
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val tokenPrefs: TokenPrefs,
    private val api: RetrofitServices
) {

    fun getToken(): String? {
        return tokenPrefs.token
    }

    fun login(
        email: String,
        password: String
    ): Flow<Resource<Unit>> {
        return flow {

            emit(Resource.Loading(true))

            val request = api.login(AuthInfo(email = email, password = password))

            val response = try {
                val responseApi = request.awaitResponse()
                if (responseApi.code() == 200) {
                    responseApi.body()?.getString("token")
                } else {
                    emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                    emit(Resource.Loading(false))
                    return@flow
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = ErrorRemote.NoInternet))
                emit(Resource.Loading(false))
                return@flow
            }

            tokenPrefs.token = response

            emit(Resource.Success(Unit))

            emit(Resource.Loading(false))
        }
    }

    fun signUp(
        email: String,
        password: String
    ): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading(true))

            val authInfo = AuthInfo(email = email, password = password)
            val request = api.signUp(authInfo)

            val response = try {
                val responseApi = request.awaitResponse()
                when {
                    responseApi.code() == 200 -> responseApi.body()
                    else -> {
                        emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                        emit(Resource.Loading(false))
                        return@flow
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = ErrorRemote.NoInternet))
                emit(Resource.Loading(false))
                return@flow
            }

            tokenPrefs.token = response?.token
            emit(Resource.Success(Unit))
            emit(Resource.Loading(false))
        }
    }

    fun editUser() = flow<Resource<User?>> {
        val editUser = EditUser(
            id = null,
            email = null,
            firstName = null,
            lastName = null,
            middleName = null,
            gender = null,
            departureCity = null,
            phone = null,
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
                emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = ErrorRemote.NoInternet))
            emit(Resource.Loading(false))
            return@flow
        }

        emit(Resource.Success(response))
        emit(Resource.Loading(false))
    }
}