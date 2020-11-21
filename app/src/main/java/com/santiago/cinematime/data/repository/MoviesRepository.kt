package com.santiago.cinematime.data.repository

import com.santiago.cinematime.data.source.LocalDataSource
import com.santiago.cinematime.data.source.RemoteDataSource
import com.santiago.cinematime.domain.Movie

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository
){
    suspend fun getPopularMovies(): List<Movie> {

        if (localDataSource.isEmpty()) {
            val movies =
                remoteDataSource.getPopularMovies(regionRepository.findLastRegion())
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getPopularMovies()
    }

    suspend fun findById(id: Int): Movie = localDataSource.findById(id)

    suspend fun update(movie: Movie) = localDataSource.update(movie)
}