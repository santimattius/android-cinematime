package com.santiago.cinematime.data.repository

import com.santiago.cinematime.data.datasources.LocalDataSource
import com.santiago.cinematime.domain.repositories.MoviesRepository
import com.santiago.cinematime.data.datasources.RemoteDataSource
import com.santiago.cinematime.domain.entities.Movie

class TheMoviesDbRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository
) : MoviesRepository {

    override suspend fun getPopularMovies(): List<Movie> {

        if (localDataSource.isEmpty()) {
            val movies =
                remoteDataSource.getPopularMovies(regionRepository.findLastRegion())
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getPopularMovies()
    }

    override suspend fun findById(id: Int): Movie = localDataSource.findById(id)

    override suspend fun update(movie: Movie) = localDataSource.update(movie)
}