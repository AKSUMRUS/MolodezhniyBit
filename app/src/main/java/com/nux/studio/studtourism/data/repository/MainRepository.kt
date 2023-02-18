package com.nux.studio.studtourism.data.repository

import android.util.Log
import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.models.Event
import com.nux.studio.studtourism.data.local.models.lab.Lab
import com.nux.studio.studtourism.data.local.models.University
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

    fun getDormitories(): Flow<Resource<List<Dormitory>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getDormitoriesList().awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                Log.d("GetDormitories", responseApi.code().toString())
                emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = ErrorRemote.NoInternet))
            Log.d("GetDormitories", e.toString())
            emit(Resource.Loading(false))
            return@flow
        }

        emit(Resource.Success(response))
        emit(Resource.Loading(false))
    }

    fun getEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getEventsList().awaitResponse()
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

    fun getLabs(): Flow<Resource<List<Lab>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getLabsList().awaitResponse()
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