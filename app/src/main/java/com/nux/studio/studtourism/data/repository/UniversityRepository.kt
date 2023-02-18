package com.nux.studio.studtourism.data.repository

import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.models.University
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.awaitResponse
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val api: RetrofitServices,
) {
    private val _universityFlow = MutableSharedFlow<Resource<University>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val universityFlow = _universityFlow.asSharedFlow()

    suspend fun loadUniversity(id: String) {
        _universityFlow.emit(Resource.Loading(true))

        val request = api.getUniversity(id)
        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _universityFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _universityFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _universityFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _universityFlow.emit(Resource.Loading(false))
            return
        }

        _universityFlow.emit(Resource.Loading(false))
        _universityFlow.emit(Resource.Success(response))
    }
}