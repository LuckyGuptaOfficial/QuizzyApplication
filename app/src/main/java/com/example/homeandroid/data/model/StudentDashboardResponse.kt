package com.example.homeandroid.data.model

import com.google.gson.annotations.SerializedName

data class StudentDashboardResponse(
    @SerializedName("student") val student: Student,
    @SerializedName("todaySummary") val todaySummary: TodaySummary,
    @SerializedName("weeklyOverview") val weeklyOverview: WeeklyOverview
)

data class Student(
    @SerializedName("name") val name: String,
    @SerializedName("class") val className: String,
    @SerializedName("availability") val availability: Availability,
    @SerializedName("quiz") val quiz: Quiz,
    @SerializedName("accuracy") val accuracy: Accuracy
)

data class Availability(
    @SerializedName("status") val status: String
)

data class Quiz(
    @SerializedName("attempts") val attempts: Int
)

data class Accuracy(
    @SerializedName("current") val current: String
)

data class TodaySummary(
    @SerializedName("mood") val mood: String,
    @SerializedName("description") val description: String,
    @SerializedName("recommendedVideo") val recommendedVideo: RecommendedVideo,
    @SerializedName("characterImage") val characterImage: String
)

data class RecommendedVideo(
    @SerializedName("title") val title: String,
    @SerializedName("actionText") val actionText: String
)

data class WeeklyOverview(
    @SerializedName("quizStreak") val quizStreak: List<QuizStreakDay>,
    @SerializedName("overallAccuracy") val overallAccuracy: OverallAccuracy,
    @SerializedName("performanceByTopic") val performanceByTopic: List<PerformanceByTopic>
)

data class QuizStreakDay(
    @SerializedName("day") val day: String,
    @SerializedName("status") val status: String
)

data class OverallAccuracy(
    @SerializedName("percentage") val percentage: Int,
    @SerializedName("label") val label: String
)

data class PerformanceByTopic(
    @SerializedName("topic") val topic: String,
    @SerializedName("trend") val trend: String
)

