package com.tmdb.app.ui.listing

import com.dbel.design.system.component.SearchViewModel
import com.tmdb.app.data.MoviesRepository
import com.tmdb.app.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : SearchViewModel<Movie>() {

    override fun doSearch(query: String) = repository.search(query)
}
