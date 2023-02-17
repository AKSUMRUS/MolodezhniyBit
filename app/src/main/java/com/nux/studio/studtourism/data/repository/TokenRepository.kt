package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.local.prefs.TokenPrefs
import com.nux.studio.studtourism.data.local.token.TokenDao
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.LoginInfo
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import javax.inject.Inject

class TokenRepository @Inject constructor(
       private val tokenPrefs: TokenPrefs
) {

    fun getToken() : String? {
        return tokenPrefs.token
    }

    fun login(
        email: String,
        password: String
    ): Flow<Resource<String>> {
        return flow {

            emit(Resource.Loading(true))

            val request = api.login(LoginInfo(email = email, password = password))

            val response = try {
                val responseApi = request.awaitResponse()
                if(responseApi.code() == 200) {
                    responseApi.body()?.getString("token")
                } else {
                    emit(Resource.Error(""))
                    null
                }
            } catch (e : Exception) {
                emit(Resource.Error(message = e.toString()))
            }

            response?.let {
//                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))

        }
    }

}