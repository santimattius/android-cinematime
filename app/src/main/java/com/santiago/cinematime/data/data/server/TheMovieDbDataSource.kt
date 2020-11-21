package com.santiago.cinematime.data.data.server

import com.santiago.cinematime.data.data.asDomainModel
import com.santiago.cinematime.data.data.server.movie.MoviesService
import com.santiago.cinematime.data.data.server.tv.TvService
import com.santiago.cinematime.data.source.RemoteDataSource
import com.santiago.cinematime.domain.Movie
import com.santiago.cinematime.domain.Tv

class TheMovieDbDataSource(private val client: TheMovieDbClient) : RemoteDataSource {

    override suspend fun getPopularMovies(region: String): List<Movie> =
        client.create<MoviesService>()
            .getPopular(region = region)
            .results
            .map { it.asDomainModel() }

    override suspend fun getPopularTvShows(region: String): List<Tv> =
        client.create<TvService>().getPopular(region = region).results.map { it.asDomainModel() }

}