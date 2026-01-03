package com.example.mycoursesapp.dii

import com.example.domain.repository.CourseRepository
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.GetFavoriteCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCoursesUseCase(
        repository: CourseRepository
    ): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteCoursesUseCase(
        repository: CourseRepository
    ): GetFavoriteCoursesUseCase {
        return GetFavoriteCoursesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(
        repository: CourseRepository
    ): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }
}