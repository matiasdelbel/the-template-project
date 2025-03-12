package com.rijks.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dbel.design.system.pane.SearchViewModel
import com.rijks.app.data.ArtCollectionRepository
import com.rijks.app.model.ArtObjectOverview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class ArtCollectionViewModel @Inject constructor(
    private val repository: ArtCollectionRepository
) : SearchViewModel<ArtObjectOverview>() {

    val artObjects = repository
        .collections()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    override fun doSearch(query: String) = repository.search(query)
}
