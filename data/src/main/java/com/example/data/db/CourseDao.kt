package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM favorite_courses")
    fun getFavoriteCourses(): Flow<List<FavoriteCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(course: FavoriteCourse)

    @Delete
    suspend fun removeFromFavorites(course: FavoriteCourse)

    @Query("SELECT EXISTS(SELECT * FROM favorite_courses WHERE courseId = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean
}