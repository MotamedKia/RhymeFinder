package com.example.rhymefinder.logics

import com.example.rhymefinder.models.poems

fun RandomIndex(list: List<poems>): Int {
    val random = list.indices.random()
    return random
}