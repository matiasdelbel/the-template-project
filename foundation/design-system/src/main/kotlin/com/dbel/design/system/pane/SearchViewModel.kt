package com.dbel.design.system.pane

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

abstract class SearchViewModel<T : Any> : ViewModel() {

    private val query = MutableStateFlow(value = "")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val state: StateFlow<SearchState<T>> = query
        .debounce(timeout = 5.milliseconds)
        .flatMapLatest { query -> doSearch(query)
            .cachedIn(viewModelScope)
            .map { result -> SearchState(query, result) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SearchState()
        )

    fun search(query: String) {
        this.query.value = query
    }

    abstract fun doSearch(query: String): Flow<PagingData<T>>
}
