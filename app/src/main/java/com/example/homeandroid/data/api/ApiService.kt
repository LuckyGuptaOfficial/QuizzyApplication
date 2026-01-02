package com.example.homeandroid.data.api

import com.example.homeandroid.data.model.StudentDashboardResponse
import retrofit2.http.GET

interface ApiService {
    @GET("o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd")
    suspend fun getStudentDashboard(): StudentDashboardResponse
}

