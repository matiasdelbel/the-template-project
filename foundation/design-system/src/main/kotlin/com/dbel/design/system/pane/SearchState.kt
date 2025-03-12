package com.dbel.design.system.pane

import androidx.paging.PagingData

data class SearchState<T : Any>(
    val query: String = "",
    val results: PagingData<T> = PagingData.empty()
)
