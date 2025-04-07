package com.example.rhymefinder.logics

import com.example.rhymefinder.models.poems

//a practical function to use in Home screen for extracting a random poem
fun RandomIndex(list: List<poems>): Int {
    val random = list.indices.random()
    return random
}