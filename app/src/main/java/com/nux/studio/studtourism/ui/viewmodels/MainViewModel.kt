package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.local.models.Committee
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.repository.MainRepository
import com.nux.studio.studtourism.ui.states.FilterState
import com.nux.studio.studtourism.ui.states.MainState
import com.nux.studio.studtourism.ui.states.RatingBy
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: MainRepository
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _state by mutableStateOf(MainState())
    val state : MainState
        get() = _state

    private var _filterState by mutableStateOf(FilterState())
    val filterState : FilterState
        get() = _filterState

    var cities: Set<String> = emptySet()
    private set

    var districts: Set<String> = emptySet()
    private set

    var committees: Set<Committee> = emptySet()
        private set

    fun filterInfo(dormitories: List<Dormitory>?) {
        if (dormitories.isNullOrEmpty()) {
            return
        }

        cities = dormitories.mapNotNull { dormitory ->
            dormitory.details?.mainInfo?.city
        }.toSet()

        districts = dormitories.mapNotNull { dormitory ->
            dormitory.details?.district
        }.toSet()

        committees = dormitories.mapNotNull { dormitory ->
            dormitory.details?.rules?.committee
        }.toSet()

    }

    fun getDormitories() {
        viewModelScope.launch {
            repository.getDormitories().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {data ->
                            _state = _state.copy(dormitoriesList = data)
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