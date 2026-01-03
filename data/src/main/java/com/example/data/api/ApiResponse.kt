package com.example.data.api

import com.example.data.dto.CourseDTO
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("courses")
    val courses: List<CourseDTO>
)
