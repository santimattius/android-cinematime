package com.santiago.cinematime.ui.model

abstract class ContentUiModel(
    val id: Int,
    val name: String,
    private val posterPath: String,
    val favorite: Boolean
) {
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}