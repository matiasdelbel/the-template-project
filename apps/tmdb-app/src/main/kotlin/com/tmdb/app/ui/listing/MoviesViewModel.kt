package com.tmdb.app.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tmdb.app.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(repository: MoviesRepository) : ViewModel() {

    val nowPlaying = repository
        .paginatedNowPlayingMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    val populars = repository
        .popularMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    val topRated = repository
        .topRatedMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    val upcoming = repository
        .upcomingMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
}
