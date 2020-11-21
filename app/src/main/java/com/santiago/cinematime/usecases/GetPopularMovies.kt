package com.santiago.cinematime.usecases

import com.santiago.cinematime.data.repository.MoviesRepository
import com.santiago.cinematime.domain.Movie

class GetPopularMovies(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(): List<Movie> = moviesRepository.getPopularMovies()
}