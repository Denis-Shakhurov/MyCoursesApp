package com.example.features.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.ui.theme.Dark
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.Green
import com.example.core.ui.theme.Grey
import com.example.core.ui.theme.LightGrey
import com.example.core.ui.theme.RobotoFontFamily
import com.example.core.ui.theme.White
import com.example.domain.model.Course
import com.example.features.utils.DateFormatter

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    MainContent(
        state = state,
        onToggleSort = viewModel::toggleSort,
        onToggleFavorite = viewModel::toggleFavorite
    )
}

@Composable
private fun MainContent(
    state: MainState,
    onToggleSort: () -> Unit,
    onToggleFavorite: (Course) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Search courses...",
                    )
                },
                modifier = Modifier.weight(1f),
                enabled = false,
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Поиск",
                        tint = White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,

                    focusedContainerColor = DarkGrey,
                    unfocusedContainerColor = DarkGrey,

                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,

                    cursorColor = White,

                    focusedTextColor = White,
                    unfocusedTextColor = White,

                    disabledBorderColor = Color.Transparent,
                    disabledContainerColor = DarkGrey,
                    disabledLabelColor = White.copy(alpha = 0.5f),
                    disabledTextColor = White,
                    disabledLeadingIconColor = White
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(DarkGrey),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {},
                    enabled = false
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                        contentDescription = "Фильтр",
                        tint = White
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "По дате добавления",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Medium,
                color = Green
            )

            IconButton(onClick = onToggleSort) {
                Icon(
                    imageVector = if (state.isSorted) Icons.Default.ArrowDownward else ImageVector.vectorResource(R.drawable.ic_arrow_doun_up),
                    contentDescription = "Сортировка",
                    tint = Green
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.courses) { course ->
                CourseCard(
                    course = course,
                    onToggleFavorite = { onToggleFavorite(course) }
                )
            }
        }
    }
}

@Composable
fun CourseCard(
    isFavorite: Boolean = course.hasLike,
    course: Course,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(DarkGrey)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Grey)
            ) {
                Button(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .align(Alignment.TopEnd),
                    onClick = onToggleFavorite,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightGrey,
                        contentColor = if (course.hasLike) Green else White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = if (course.hasLike) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "В избранное"
                    )
                }

                Row(
                    modifier = Modifier.align(Alignment.BottomStart),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(LightGrey)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.Star,
                                contentDescription = "Рейтинг",
                                tint = Green
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = course.rate,
                                color = White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(LightGrey)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = DateFormatter.formatDate(course.startDate),
                            color = White
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = course.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = White
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                text = course.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = White.copy(alpha = 0.5f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = course.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )

                TextButton(
                    onClick = {},
                    enabled = false
                ) {
                    Text(
                        text = "Подробнее ->",
                        color = Green
                    )
                }
            }
        }
    }
}