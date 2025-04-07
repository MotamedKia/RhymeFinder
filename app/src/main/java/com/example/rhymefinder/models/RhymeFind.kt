package com.example.rhymefinder.models

import kotlinx.serialization.Serializable

//the original Database
@Serializable
data class RhymeFind(
    val current_page: Int,
    val data_items: List<DataItem>,
    val error: String,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val next_page_url: String?,
    val per_page: Int,
    val prev_page_url: String?,
    val to: Int,
    val total: Int
) {

}