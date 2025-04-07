package com.example.rhymefinder.models

import kotlinx.serialization.Serializable

//called in RhymeFind.kt
@Serializable
data class DataItem(
    val id: Int,
    val word: String
)