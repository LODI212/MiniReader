package com.example.minireader

data class Book(
    val id: Int,
    val title: String,
    val cover: String,
    val text: String,
    val image: String,
    val description: String,
    val url: String? = null,

    var isAdded: Boolean = false
)

