package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.prefs.TokenPrefs
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.LoginInfo
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

            val request = api.login(LoginInfo(email = email, password = password))

            val response = try {
                val responseApi = request.awaitResponse()
                if(responseApi.code() == 200) {
                    responseApi.body()?.getString("token")
                } else {
                    emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                    return@flow
                }
            } catch (e : Exception) {
                emit(Resource.Error(message = ErrorRemote.NoInternet))
                return@flow
            }

            tokenPrefs.token = response

            emit(Resource.Success(Unit))

            emit(Resource.Loading(false))
        }
    }

}