package com.example.data.mapper

import com.example.data.db.FavoriteCourse
import com.example.data.dto.CourseDTO
import com.example.domain.model.Course

fun CourseDTO.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = text,
        price = price,
        rate = rate,
        startDate = startDate,
        publishDate = publishDate,
        hasLike = hasLike
    )
}

fun FavoriteCourse.toDomain(): Course {
    return Course(
        id = courseId,
        title = title,
        description = description,
        price = price,
        rate = rate,
        startDate = startDate,
        publishDate = publishDate,
        hasLike = true
    )
}

fun Course.toFavoriteEntity(): FavoriteCourse {
    return FavoriteCourse(
        courseId = id,
        title = title,
        description = description,
        price = price,
        rate = rate,
        startDate = startDate,
        publishDate = publishDate,
    )
}