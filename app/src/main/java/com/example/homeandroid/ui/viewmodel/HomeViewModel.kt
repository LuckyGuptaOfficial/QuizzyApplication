package com.example.homeandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeandroid.data.model.StudentDashboardResponse
import com.example.homeandroid.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val dashboardData: StudentDashboardResponse? = null
)

class HomeViewModel : ViewModel() {
    private val dashboardRepository = DashboardRepository()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    fun loadDashboardData() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = dashboardRepository.getStudentDashboard()
            result.fold(
                onSuccess = { data ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        dashboardData = data,
                        errorMessage = null
                    )
                },
                onFailure = { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        dashboardData = null,
                        errorMessage = exception.message ?: "Failed to load dashboard data"
                    )
                }
            )
        }
    }

    fun retry() {
        loadDashboardData()
    }
}

