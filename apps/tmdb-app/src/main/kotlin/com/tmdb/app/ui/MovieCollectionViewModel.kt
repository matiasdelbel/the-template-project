package com.tmdb.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tmdb.app.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MovieCollectionViewModel @Inject constructor(
    repository: MoviesRepository
) : ViewModel() {

    val populars = repository
        .paginatedPopularMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
}
