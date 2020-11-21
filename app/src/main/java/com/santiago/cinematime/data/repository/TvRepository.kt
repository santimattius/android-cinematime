package com.santiago.cinematime.data.repository

import com.santiago.cinematime.data.source.LocalDataSource
import com.santiago.cinematime.data.source.RemoteDataSource
import com.santiago.cinematime.domain.Tv

class TvRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository
) {
    suspend fun getPopular(): List<Tv> {
        return remoteDataSource.getPopularTvShows(regionRepository.findLastRegion())
    }

}