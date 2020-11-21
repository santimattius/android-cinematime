package com.santiago.cinematime.usecases

import com.santiago.cinematime.data.repository.TvRepository
import com.santiago.cinematime.domain.Tv

class GetPopularTvShows(private val repository: TvRepository) {

    suspend fun invoke(): List<Tv> = repository.getPopular()
}