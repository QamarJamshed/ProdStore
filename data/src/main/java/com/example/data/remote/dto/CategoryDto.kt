package com.example.data.remote.dto

import com.example.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(val slug: String,  val name: String)

fun CategoryDto.toDomain(): Category {
    return Category(slug = slug, name = name)
}