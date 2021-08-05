package com.santiago.cinematime.data.repository

import com.santiago.cinematime.data.datasources.RemoteDataSource
import com.santiago.cinematime.domain.entities.Tv
import com.santiago.cinematime.domain.repositories.TvShowsRepository

class TheMovieDbTvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository
) : TvShowsRepository {
    override suspend fun getPopular(): List<Tv> {
        return remoteDataSource.getPopularTvShows(regionRepository.findLastRegion())
    }
}