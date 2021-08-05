package com.santiago.cinematime.data.datasources

import com.santiago.cinematime.domain.entities.Movie
import com.santiago.cinematime.domain.entities.Tv

interface RemoteDataSource {

    suspend fun getPopularMovies(region: String): List<Movie>
    suspend fun getPopularTvShows(region: String): List<Tv>


}