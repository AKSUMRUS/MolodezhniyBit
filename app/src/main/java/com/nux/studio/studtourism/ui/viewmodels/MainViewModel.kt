package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.local.models.Committee
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.models.DormitoryBookingRequest
import com.nux.studio.studtourism.data.local.models.Event
import com.nux.studio.studtourism.data.repository.MainRepository
import com.nux.studio.studtourism.data.repository.ProfileRepository
import com.nux.studio.studtourism.ui.states.FilterState
import com.nux.studio.studtourism.ui.states.MainState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: MainRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _state by mutableStateOf(MainState())
    val state: MainState
        get() = _state

    var filterState by mutableStateOf(FilterState())

    var cities: Set<String> = emptySet()
        private set

    var districts: Set<String> = emptySet()
        private set

    var committees: Set<Committee> = emptySet()
        private set

    init {
        loadProfile()
        subscribeEditProfileFlow()
        subscribeDormitoriesFlow()
        subscribeProfileFlow()
    }

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

    fun updateFilters(
        filters: FilterState
    ) {
        filterState = filters
    }

    fun makeDormitoryBooking(
        bookingRequest: DormitoryBookingRequest
    ) {
        viewModelScope.launch {
            repository.makeDormitoryBooking(
                booking = bookingRequest
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {

                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _state = _state.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

    fun getDormitories() {
        viewModelScope.launch {
            repository.getDormitories()
        }
    }

    private fun subscribeDormitoriesFlow() {
        viewModelScope.launch {
            repository.dormitoriesFlow.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            _state = _state.copy(dormitoriesList = data)
                            filterInfo(data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
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
                        result.data?.let { data ->
                            _state = _state.copy(eventsList = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
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
                        result.data?.let { data ->
                            _state = _state.copy(labsList = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _state = _state.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

    fun getDormitoriesBooked() {
        viewModelScope.launch {
            repository.getDormitoriesBooked().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            _state = _state.copy(dormitoriesBookedList = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _state = _state.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _state = _state.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            profileRepository.loadProfile()
        }
    }

    private fun subscribeEditProfileFlow() {
        viewModelScope.launch {
            profileRepository.editUserFlow.collectLatest { result ->

                if (result is Resource.Success) {
                    val starredDormitories = when {
                        result.data?.starredDormitories != null -> result.data.starredDormitories.toSet()
                        else -> _state.starredDormitories
                    }
                    val starredEvents = when {
                        result.data?.starredEvents != null -> result.data.starredEvents.toSet()
                        else -> _state.starredEvents
                    }
                    _state = _state.copy(
                        starredDormitories = starredDormitories,
                        starredEvents = starredEvents
                    )
                }
            }
        }
    }

    private fun subscribeProfileFlow() {
        viewModelScope.launch {
            profileRepository.profileFlow.collectLatest { result ->
                if (result is Resource.Success) {
                    val starredDormitories = when {
                        result.data?.starredDormitories != null -> result.data.starredDormitories.toSet()
                        else -> _state.starredDormitories
                    }
                    val starredEvents = when {
                        result.data?.starredEvents != null -> result.data.starredEvents.toSet()
                        else -> _state.starredEvents
                    }
                    _state = _state.copy(
                        starredDormitories = starredDormitories,
                        starredEvents = starredEvents
                    )
                }
            }
        }
    }

    fun setIndexView(index: Int) {
        _state = _state.copy(indexView = index)
    }

    fun favouriteDormitory(dormitory: Dormitory, isStarred: Boolean) {
        val starredDormitories = when {
            isStarred -> _state.starredDormitories.plus(dormitory.id)
            else -> _state.starredDormitories.minus(dormitory.id)
        }

        viewModelScope.launch {
            profileRepository.editUser(
                starredDormitories = starredDormitories
            )
        }
    }

    fun favouriteEvent(event: Event, isStarred: Boolean) {
        val starredEvents = when {
            isStarred -> _state.starredEvents.plus(event.id)
            else -> _state.starredEvents.minus(event.id)
        }

        viewModelScope.launch {
            profileRepository.editUser(
                starredEvents = starredEvents
            )
        }
    }
}