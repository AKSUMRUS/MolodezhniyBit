package com.nux.studio.studtourism.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nux.studio.studtourism.data.repository.AuthRepository
import com.nux.studio.studtourism.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel()