package com.nux.studio.studtourism.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun signUp(
        email: String,
        password: String,
        fistName: String,
        lastName: String,
        middleName: String,
        phone: String,
    ) {
        viewModelScope.launch {
            authRepository.signUp(
                email = email,
                password = password,
                firstName = fistName,
                lastName = lastName,
                middleName = middleName,
                phone = phone,
            )
        }
    }
}