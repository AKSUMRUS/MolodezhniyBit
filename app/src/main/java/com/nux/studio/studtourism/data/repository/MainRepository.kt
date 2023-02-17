package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.university.University
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: RetrofitServices
) {

    fun getUniversities(): Flow<Resource<List<University>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getUniversitiesList().awaitResponse()
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