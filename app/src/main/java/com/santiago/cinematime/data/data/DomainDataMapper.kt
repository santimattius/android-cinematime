package com.santiago.cinematime.data.data

import com.santiago.cinematime.data.data.database.movie.MovieEntity
import com.santiago.cinematime.domain.Movie
import com.santiago.cinematime.domain.Tv
import com.santiago.cinematime.data.data.server.movie.Movie as ServerMovie
import com.santiago.cinematime.data.data.server.tv.Tv as ServerTv

fun Movie.asDatabaseModel(): MovieEntity =
    MovieEntity(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite
    )

fun MovieEntity.asDomainModel(): Movie = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    favorite
)

fun ServerMovie.asDomainModel(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        posterPath.orEmpty(),
        backdropPath.orEmpty(),
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        false
    )

fun ServerTv.asDomainModel(): Tv {
    return Tv(
        id,
        firstAirDate,
        overview,
        originalLanguage,
        genreIds,
        posterPath,
        originCountry,
        backdropPath,
        originalName,
        popularity,
        voteAverage,
        name,
        voteCount
    )
}
