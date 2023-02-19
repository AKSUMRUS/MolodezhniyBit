package com.nux.studio.studtourism.ui.states

import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.models.DormitoryBooked
import com.nux.studio.studtourism.data.local.models.Event
import com.nux.studio.studtourism.data.local.models.lab.Lab

data class MainState(
    val dormitoriesList: List<Dormitory> = emptyList(),
    val eventsList: List<Event> = emptyList(),
    val labsList: List<Lab> = emptyList(),
    val dormitoriesBookedList: List<DormitoryBooked> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val indexView: Int = 0,
)
