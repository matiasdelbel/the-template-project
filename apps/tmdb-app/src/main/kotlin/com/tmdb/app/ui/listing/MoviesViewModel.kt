package com.tmdb.app.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dbel.design.system.pane.SearchViewModel
import com.tmdb.app.data.MoviesRepository
import com.tmdb.app.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : SearchViewModel<Movie>() {

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

    override fun doSearch(query: String) = repository.search(query)
}
