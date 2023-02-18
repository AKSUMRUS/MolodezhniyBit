package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.AuthRepository
import com.nux.studio.studtourism.ui.states.SignUpState
import com.nux.studio.studtourism.ui.viewmodels.error.ErrorMapper
import com.nux.studio.studtourism.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val errorMapper = ErrorMapper(context)
    private var _state by mutableStateOf(SignUpState())
    val state: SignUpState
        get() = _state

    init {
        subscribeSignUpFlow()
        subscribeLoginFlow()
    }

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

    fun login(
        email: String,
        password: String
    ){
        viewModelScope.launch {
            authRepository.login(
                email = email,
                password = password
            )
        }
    }

    private fun subscribeSignUpFlow() {
        viewModelScope.launch {
            authRepository.signUpFlow.collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            Log.e("SIGNUP", "SUCCESS")
                            _state = _state.copy(isSuccess = true)
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

    private fun subscribeLoginFlow() {
        viewModelScope.launch {
            authRepository.loginFlow.collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _state = _state.copy(isSuccess = true)
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
}