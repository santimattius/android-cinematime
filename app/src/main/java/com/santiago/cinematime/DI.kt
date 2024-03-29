package com.santiago.cinematime

import android.app.Application
import com.santiago.cinematime.data.data.AndroidPermissionChecker
import com.santiago.cinematime.data.data.database.MovieDatabase
import com.santiago.cinematime.data.data.database.RoomDataSource
import com.santiago.cinematime.data.data.location.PlayServicesLocationDataSource
import com.santiago.cinematime.data.data.server.TheMovieDbClient
import com.santiago.cinematime.data.data.server.TheMovieDbDataSource
import com.santiago.cinematime.data.datasources.LocalDataSource
import com.santiago.cinematime.data.datasources.LocationDataSource
import com.santiago.cinematime.data.datasources.RemoteDataSource
import com.santiago.cinematime.data.repository.PermissionChecker
import com.santiago.cinematime.data.repository.RegionRepository
import com.santiago.cinematime.data.repository.TheMovieDbTvShowRepository
import com.santiago.cinematime.data.repository.TheMoviesDbRepository
import com.santiago.cinematime.domain.repositories.MoviesRepository
import com.santiago.cinematime.domain.repositories.TvShowsRepository
import com.santiago.cinematime.domain.usecases.GetPopularMovies
import com.santiago.cinematime.domain.usecases.GetPopularTvShows
import com.santiago.cinematime.ui.MainActivity
import com.santiago.cinematime.ui.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private const val API_KEY_NAMED = "api_key"
private const val BASE_URL_NAMED = "base_url"

private val appModule = module {
    single(named(API_KEY_NAMED)) { BuildConfig.API_KEY }
    single(named(BASE_URL_NAMED)) { androidApplication().getString(R.string.base_url) }
    single { MovieDatabase.build(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single { TheMovieDbClient(get(named(BASE_URL_NAMED)), get(named(API_KEY_NAMED))) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { TheMovieDbDataSource(get()) }
    factory<LocationDataSource> {
        PlayServicesLocationDataSource(
            get()
        )
    }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
}

val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory<MoviesRepository> { TheMoviesDbRepository(get(), get(), get()) }
    factory<TvShowsRepository> { TheMovieDbTvShowRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetPopularMovies(get()) }
        scoped { GetPopularTvShows(get()) }
    }
}