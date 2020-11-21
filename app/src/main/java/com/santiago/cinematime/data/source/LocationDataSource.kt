package com.santiago.cinematime.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}