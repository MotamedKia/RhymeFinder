package com.example.rhymefinder.models

import kotlinx.serialization.Serializable

@Serializable
data class DataItem(
    val id: Int,
    val word: String
)