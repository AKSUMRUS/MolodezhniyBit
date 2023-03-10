package com.nux.studio.studtourism.data.repository

import android.util.Log
import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.models.*
import com.nux.studio.studtourism.data.local.models.lab.Lab
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: RetrofitServices
) {

    private val _dormitoriesFlow = MutableSharedFlow<Resource<List<Dormitory>>>(
        replay = 3
    )
    val dormitoriesFlow = _dormitoriesFlow.asSharedFlow()

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
    fun makeDormitoryBooking(
        booking : DormitoryBookingRequest
    ): Flow<Resource<Any>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.makeDormitoryBooking(booking).awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                Log.e("BOOKING", responseApi.code().toString())
                Log.e("BOOKING", responseApi.errorBody().toString())
                emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            Log.e("BOOKING", e.toString())
            emit(Resource.Error(message = ErrorRemote.NoInternet))
            emit(Resource.Loading(false))
            return@flow
        }

        emit(Resource.Success(Unit))
        emit(Resource.Loading(false))
    }

    suspend fun cancelDormitoryBooking(
        booking: CancelBooking
    ) {
        withContext(Dispatchers.IO) {
            api.cancelDormitoryBooking(booking).execute()
        }
    }

    suspend fun getDormitories() {
        _dormitoriesFlow.emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getDormitoriesList().awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _dormitoriesFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _dormitoriesFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _dormitoriesFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _dormitoriesFlow.emit(Resource.Loading(false))
            return
        }

        _dormitoriesFlow.emit(Resource.Success(response))
        _dormitoriesFlow.emit(Resource.Loading(false))
    }

    fun getEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getEventsList().awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                Log.d("EventsScreen", responseApi.code().toString())
                emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            Log.d("EventsScreen", e.toString())
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
                Log.d("GetLabs", responseApi.code().toString())
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            Log.d("GetLabs", e.toString())
            emit(Resource.Error(message = ErrorRemote.NoInternet))
            emit(Resource.Loading(false))
            return@flow
        }

        emit(Resource.Success(response))
        emit(Resource.Loading(false))
    }
    fun getDormitoriesBooked(): Flow<Resource<List<DormitoryBooked>>> = flow {
        emit(Resource.Loading(true))
        val response = try {
            val responseApi = api.getDormitoriesBookedList().awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                emit(Resource.Loading(false))
                return@flow
            }
        } catch (e: Exception) {
            Log.d("GetDormitoriesBookedException", e.toString())
            emit(Resource.Error(message = ErrorRemote.NoInternet))
            emit(Resource.Loading(false))
            return@flow
        }

        emit(Resource.Success(response))
        emit(Resource.Loading(false))
    }
}