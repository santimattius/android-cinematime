package com.santiago.cinematime.ui

import com.santiago.cinematime.ui.model.ContentUiModel
import com.santiago.cinematime.ui.model.MovieUiModel
import com.santiago.cinematime.ui.model.TvUiModel

sealed class MainState {
    object Loading : MainState()
    object Complete : MainState()
    data class Movies(val movies: List<MovieUiModel>) : MainState()
    data class TvShows(val tvShows: List<TvUiModel>) : MainState()
    data class Navigation(val content: ContentUiModel) : MainState()
    object RequestLocationPermission : MainState()
}