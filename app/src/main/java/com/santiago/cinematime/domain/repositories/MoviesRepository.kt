package com.santiago.cinematime.domain.repositories

import com.santiago.cinematime.domain.entities.Movie

interface MoviesRepository {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun findById(id: Int): Movie

    suspend fun update(movie: Movie)
}