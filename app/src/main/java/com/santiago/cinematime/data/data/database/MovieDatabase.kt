package com.santiago.cinematime.data.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santiago.cinematime.data.data.database.movie.MovieEntity
import com.santiago.cinematime.data.data.database.movie.MovieDao

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    companion object {

        private const val DATA_BASE_NAME = "movie_db"
        fun build(context: Context) = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }

    abstract fun movieDao(): MovieDao
}