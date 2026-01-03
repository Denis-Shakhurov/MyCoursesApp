package com.example.features.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.GetFavoriteCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavoritesState())
    val state = _state.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val courses = getFavoriteCoursesUseCase().first()
            _state.update {
                it.copy(
                    favorites = courses,
                    isLoading = false
                )
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            val updatedFavorites = _state.value.favorites.filter {
                it.id != course.id
            }
            _state.update {
                it.copy(favorites = updatedFavorites)
            }
        }
    }
}

data class FavoritesState(
    val favorites: List<Course> = emptyList(),
    val isLoading: Boolean = false,
)