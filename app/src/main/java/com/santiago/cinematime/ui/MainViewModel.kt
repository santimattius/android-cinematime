package com.santiago.cinematime.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.cinematime.ui.model.ContentUiModel
import com.santiago.cinematime.ui.model.MovieUiModel
import com.santiago.cinematime.ui.model.TvUiModel
import com.santiago.cinematime.usecases.GetPopularMovies
import com.santiago.cinematime.usecases.GetPopularTvShows
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val getPopularTvShows: GetPopularTvShows
) : ViewModel() {

    private val _model = MutableLiveData<MainState>()
    val model: LiveData<MainState>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private fun refresh() {
        emit(MainState.RequestLocationPermission)
    }

    fun onCoarsePermissionRequested() {
        viewModelScope.launch {
            emit(MainState.Loading)
            val movies = async { getPopularMovies.invoke().asMovieUiCollection() }
            val tvShows = async { getPopularTvShows.invoke().asTvUiCollection() }
            emit(MainState.Movies(movies.await()))
            emit(MainState.TvShows(tvShows.await()))
            delay(1000)
            emit(MainState.Complete)
        }
    }

    private fun emit(state: MainState) {
        _model.postValue(state)
    }

    fun onContentClicked(content: ContentUiModel) {
        emit(MainState.Navigation(content))
    }

}

sealed class MainState {
    object Loading : MainState()
    object Complete : MainState()
    data class Movies(val movies: List<MovieUiModel>) : MainState()
    data class TvShows(val tvShows: List<TvUiModel>) : MainState()
    data class Navigation(val content: ContentUiModel) : MainState()
    object RequestLocationPermission : MainState()
}
