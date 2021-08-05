package com.santiago.cinematime.data.datasources

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}