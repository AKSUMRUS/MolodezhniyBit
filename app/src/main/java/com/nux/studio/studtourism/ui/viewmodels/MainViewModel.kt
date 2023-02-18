package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.MainRepository
import com.nux.studio.studtourism.ui.states.MainState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: MainRepository
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _state by mutableStateOf(MainState())
    val state : MainState
        get() = _state

    init {
        getDormitories()
    }

    private fun getDormitories() {
        viewModelScope.launch {
            repository.getDormitories().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {data ->
                            _state = _state.copy(dormitoriesList = data)
                            Log.d("GetDormitories", data.toString())
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let {data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let {error ->
                            _state = _state.copy(error = errorMapper.map(error))
                            Log.d("GetDormitories", error.toString())
                        }
                    }
                }
            }
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            repository.getEvents().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {data ->
                            _state = _state.copy(eventsList = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let {data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let {error ->
                            _state = _state.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

    fun getLabs() {
        viewModelScope.launch {
            repository.getLabs().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {data ->
                            _state = _state.copy(labsList = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let {data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let {error ->
                            _state = _state.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

}