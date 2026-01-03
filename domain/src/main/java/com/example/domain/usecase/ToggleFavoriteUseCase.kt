package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository

class ToggleFavoriteUseCase (
    private val repository: CourseRepository
) {
    suspend operator fun invoke(course: Course) {
        repository.toggleFavorite(course)
    }
}