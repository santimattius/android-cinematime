package com.santiago.cinematime.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.cinematime.domain.usecases.GetPopularMovies
import com.santiago.cinematime.domain.usecases.GetPopularTvShows
import com.santiago.cinematime.ui.model.ContentUiModel
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
            val movies = async { getPopularMovies().asMovieUiCollection() }
            val tvShows = async { getPopularTvShows().asTvUiCollection() }
            emit(MainState.Movies(movies.await()))
            emit(MainState.TvShows(tvShows.await()))
            delay(DELAY)
            emit(MainState.Complete)
        }
    }

    private fun emit(state: MainState) {
        _model.postValue(state)
    }

    fun onContentClicked(content: ContentUiModel) {
        emit(MainState.Navigation(content))
    }

    companion object {
        private const val DELAY = 1_000L
    }
}