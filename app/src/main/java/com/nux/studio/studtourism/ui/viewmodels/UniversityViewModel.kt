package com.nux.studio.studtourism.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nux.studio.studtourism.data.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityRepository: UniversityRepository,
) : ViewModel() {

    val universityFlow = universityRepository.universityFlow

    fun loadUniversity(id: String) {
        viewModelScope.launch {
            universityRepository.loadUniversity(id)
        }
    }
}