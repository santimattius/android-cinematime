package com.santiago.cinematime.data.data.database.movie

import androidx.room.*
import com.santiago.cinematime.data.data.database.movie.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM table_movie order by popularity desc")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM table_movie WHERE id = :id")
    fun findById(id: Int): MovieEntity

    @Query("SELECT COUNT(id) FROM table_movie")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}