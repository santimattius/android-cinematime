package com.santiago.cinematime.data.data.server.tv

import com.santiago.cinematime.data.data.server.PagingResponse
import com.santiago.cinematime.data.data.server.TheMovieDbClient
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    @GET("/{version}/tv/popular")
    suspend fun getPopular(
        @Path("version") version: Int = TheMovieDbClient.DEFAULT_VERSION,
        @Query("region") region: String
    ): PagingResponse<Tv>
}
