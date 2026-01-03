package com.example.domain.repository

import com.example.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun getCourses(): List<Course>

    fun getFavoriteCourses(): Flow<List<Course>>

    suspend fun toggleFavorite(course: Course)

    suspend fun isFavorite(courseId: Int): Boolean
}