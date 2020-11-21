package com.santiago.cinematime.data.source

import com.santiago.cinematime.domain.Movie
import com.santiago.cinematime.domain.Tv

interface RemoteDataSource {

    suspend fun getPopularMovies(region: String): List<Movie>
    suspend fun getPopularTvShows(region: String): List<Tv>


}