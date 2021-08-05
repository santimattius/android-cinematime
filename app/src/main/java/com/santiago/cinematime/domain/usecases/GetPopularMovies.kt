package com.santiago.cinematime.domain.usecases

import com.santiago.cinematime.domain.entities.Movie
import com.santiago.cinematime.domain.repositories.MoviesRepository

class GetPopularMovies(private val repository: MoviesRepository) {
    suspend operator fun invoke(): List<Movie> = repository.getPopularMovies()
}