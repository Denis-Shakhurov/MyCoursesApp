package com.example.data.repository

import android.util.Log
import com.example.data.api.CourseApi
import com.example.data.db.CourseDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toFavoriteEntity
import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val dao: CourseDao
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        return try {
            api.getCourses().courses.map { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Course>()
        }
    }

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return dao.getFavoriteCourses().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(course: Course) {
        if (dao.isFavorite(course.id)) {
            dao.removeFromFavorites(course.toFavoriteEntity())
        } else {
            dao.addToFavorites(course.toFavoriteEntity())
        }
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return dao.isFavorite(courseId)
    }
}