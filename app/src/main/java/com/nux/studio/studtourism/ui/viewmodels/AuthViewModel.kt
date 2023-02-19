package com.nux.studio.studtourism.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.nux.studio.studtourism.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel()