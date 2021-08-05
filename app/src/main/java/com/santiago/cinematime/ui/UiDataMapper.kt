package com.santiago.cinematime.ui

import com.santiago.cinematime.ui.model.MovieUiModel
import com.santiago.cinematime.ui.model.TvUiModel
import com.santiago.cinematime.domain.entities.Movie as MovieDomain
import com.santiago.cinematime.domain.entities.Tv as TvDomain

fun MovieDomain.asUiModel() =
    MovieUiModel(id, title, posterPath, favorite)

fun List<MovieDomain>.asMovieUiCollection(): List<MovieUiModel> = map { it.asUiModel() }

fun TvDomain.asUiModel() =
    TvUiModel(id, name, posterPath.orEmpty(), favorite = false)

fun List<TvDomain>.asTvUiCollection(): List<TvUiModel> = map { it.asUiModel() }