package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.MainRepository
import com.nux.studio.studtourism.ui.states.MapState
import com.nux.studio.studtourism.ui.states.ProfileState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)

    private var _mapState = MutableStateFlow(MapState())
    val mapState: Flow<MapState>
        get() = _mapState

    init {
        loadDormitories()
        subscribeDormitoriesFlow()
    }
    private fun loadDormitories() {
        viewModelScope.launch {
            mainRepository.getDormitories()
        }
    }

    private fun subscribeDormitoriesFlow() {
        viewModelScope.launch {
            mainRepository.dormitoriesFlow.collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            _mapState.value = _mapState.value.copy(dormitories = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _mapState.value = _mapState.value.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _mapState.value = _mapState.value.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }
}