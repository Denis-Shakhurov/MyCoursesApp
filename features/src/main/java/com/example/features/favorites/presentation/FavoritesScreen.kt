package com.example.features.favorites.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.Dark
import com.example.core.ui.theme.RobotoFontFamily
import com.example.core.ui.theme.White
import com.example.domain.model.Course
import com.example.features.main.presentation.CourseCard

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    FavoritesContent(
        state = state,
        onToggleFavorite = viewModel::toggleFavorite
    )
}

@Composable
private fun FavoritesContent(
    state: FavoritesState,
    onToggleFavorite: (Course) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Избранное",
            fontSize = 16.sp,
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Medium,
            color = White
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.favorites.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Нет избранных курсов",
                        fontSize = 16.sp,
                        fontFamily = RobotoFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                }
            }

            else -> {
                FavoriteCoursesList(
                    favorites = state.favorites,
                    onToggleFavorite = onToggleFavorite
                )
            }
        }
    }
}

@Composable
private fun FavoriteCoursesList(
    favorites: List<Course>,
    onToggleFavorite: (Course) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(favorites, key = { it.id }) { course ->
            CourseCard(
                course = course,
                isFavorite = true,
                onToggleFavorite = { onToggleFavorite(course) }
            )
        }
    }
}