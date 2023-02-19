package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.UniversityRepository
import com.nux.studio.studtourism.ui.states.UniversityState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val universityRepository: UniversityRepository,
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _state by mutableStateOf(UniversityState())
    val state : UniversityState
        get() = _state

    init {
        subscribeUniversityFlow()
    }

    fun loadUniversity(id: String) {
        viewModelScope.launch {
            universityRepository.loadUniversity(id)
        }
    }

    private fun subscribeUniversityFlow() {
        viewModelScope.launch {
            universityRepository.universityFlow.collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {data ->
                            _state = _state.copy(university = data)
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