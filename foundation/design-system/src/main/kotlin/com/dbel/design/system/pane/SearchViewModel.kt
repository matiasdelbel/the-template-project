package com.dbel.design.system.pane

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlin.time.Duration.Companion.milliseconds

abstract class SearchViewModel<T : Any> : ViewModel() {

    private val _query = MutableStateFlow(value = "")

    val query: StateFlow<String> = _query

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val results = query
        .debounce(timeout = 5.milliseconds)
        .flatMapLatest { doSearch(query = it) }
        .cachedIn(viewModelScope)

    fun search(query: String) {
        _query.value = query
    }

    abstract fun doSearch(query: String): Flow<PagingData<T>>
}
