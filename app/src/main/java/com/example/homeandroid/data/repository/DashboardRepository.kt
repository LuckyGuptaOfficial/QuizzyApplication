package com.example.homeandroid.data.repository

import com.example.homeandroid.data.api.RetrofitClient
import com.example.homeandroid.data.model.StudentDashboardResponse

class DashboardRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getStudentDashboard(): Result<StudentDashboardResponse> {
        return try {
            val response = apiService.getStudentDashboard()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

