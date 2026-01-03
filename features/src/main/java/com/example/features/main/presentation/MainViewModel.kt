package com.example.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        loadCourses()
    }

private fun loadCourses() {
    viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        val courses = getCoursesUseCase()
        _state.update {
            it.copy(
                courses = courses,
                originalCourses = courses,
                isLoading = false
            )
        }
    }
}

    fun toggleSort() {
        _state.update { currentState ->
            val isSorted = !currentState.isSorted
            val sortedCourses = if (isSorted) {
                currentState.courses.sortedByDescending { it.publishDate }
            } else {
                currentState.originalCourses
            }
            currentState.copy(
                courses = sortedCourses,
                isSorted = isSorted
            )
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            val updatedCourses = _state.value.courses.map {
                if (it.id == course.id) {
                    it.copy(hasLike = !it.hasLike)
                } else {
                    it
                }
            }
            _state.update { it.copy(courses = updatedCourses) }
        }
    }
}

data class MainState(
    val courses: List<Course> = emptyList(),
    val originalCourses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSorted: Boolean = false
)