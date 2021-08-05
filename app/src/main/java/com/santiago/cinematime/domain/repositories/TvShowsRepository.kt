package com.santiago.cinematime.domain.repositories

import com.santiago.cinematime.domain.entities.Tv

interface TvShowsRepository {
    suspend fun getPopular(): List<Tv>
}