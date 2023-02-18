package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.ProfileRepository
import com.nux.studio.studtourism.ui.states.EditProfileState
import com.nux.studio.studtourism.ui.states.ProfileState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _profileState by mutableStateOf(ProfileState())
    val profileState: ProfileState
        get() = _profileState

    private var _editProfileState by mutableStateOf(EditProfileState())
    val editProfileState: EditProfileState
        get() = _editProfileState

    init {
        loadProfile()
        subscribeProfileFlow()
        subscribeEditProfileState()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            profileRepository.loadProfile()
        }
    }

    fun editProfile(
        id: String? = null,
        email: String? = null,
        gender: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        middleName: String? = null,
        phone: String? = null,
        departureCity: String? = null,
        socialUrl: String? = null,
        universityName: String? = null,
        avatar: String? = null,
        birthday: String? = null,
        WoS: String? = null,
        WoS1: String? = null,
        studentRoleType: String? = null,
    ) {
        viewModelScope.launch {
            profileRepository.editUser(
                id = id,
                email = email,
                gender = gender,
                firstName = firstName,
                lastName = lastName,
                middleName = middleName,
                phone = phone,
                departureCity = departureCity,
                socialUrl = socialUrl,
                universityName = universityName,
                avatar = avatar,
                birthday = birthday,
                WoS = WoS,
                WoS1 = WoS1,
                studentRoleType = studentRoleType,
            )
        }
    }

    private fun subscribeProfileFlow() {
        viewModelScope.launch {
            profileRepository.profileFlow.collectLatest { result ->
                Log.d("RRR, res", "$result")
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            _profileState = _profileState.copy(user = data)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _profileState = _profileState.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _profileState = _profileState.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }

    private fun subscribeEditProfileState() {
        viewModelScope.launch {
            profileRepository.editUserFlow.collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _editProfileState = _editProfileState.copy(isSuccess = true)
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { data ->
                            _editProfileState = _editProfileState.copy(isLoading = data)
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { error ->
                            _editProfileState =
                                _editProfileState.copy(error = errorMapper.map(error))
                        }
                    }
                }
            }
        }
    }
}