package com.santiago.cinematime.data.data.database

import com.santiago.cinematime.data.data.asDomainModel
import com.santiago.cinematime.data.data.asDatabaseModel
import com.santiago.cinematime.data.source.LocalDataSource
import com.santiago.cinematime.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: MovieDatabase) : LocalDataSource {

    private val movieDao = db.movieDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }

    override suspend fun saveMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) { movieDao.insertMovies(movies.map { it.asDatabaseModel() }) }
    }

    override suspend fun getPopularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.getAll().map { it.asDomainModel() }
    }

    override suspend fun findById(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.findById(id).asDomainModel()
    }

    override suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) { movieDao.updateMovie(movie.asDatabaseModel()) }
    }
}