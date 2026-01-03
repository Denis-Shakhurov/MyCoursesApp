package com.example.domain.usecase

import com.example.domain.repository.CourseRepository

class IsFavoriteUseCase (
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int): Boolean = repository.isFavorite(courseId)
}