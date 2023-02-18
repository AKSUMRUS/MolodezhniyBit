package com.nux.studio.studtourism.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val profileFlow = profileRepository.profileFlow
    val editUser = profileRepository.editUserFlow

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            profileRepository.loadProfile()
        }
    }

    fun editProfile(
        firstName: String? = null,
        lastName: String? = null,
        middleName: String? = null,
        phone: String? = null
    ) {
        viewModelScope.launch {
            profileRepository.editUser(
                firstName = firstName,
                lastName = lastName,
                middleName = middleName,
                phone = phone
            )
        }
    }

}