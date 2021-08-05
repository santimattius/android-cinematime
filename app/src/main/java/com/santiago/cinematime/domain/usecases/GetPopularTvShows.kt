package com.santiago.cinematime.domain.usecases

import com.santiago.cinematime.domain.entities.Tv
import com.santiago.cinematime.domain.repositories.TvShowsRepository

class GetPopularTvShows(private val repository: TvShowsRepository) {
    suspend operator fun invoke(): List<Tv> = repository.getPopular()
}