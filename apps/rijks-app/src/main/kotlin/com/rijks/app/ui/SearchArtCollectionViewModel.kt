package com.rijks.app.ui

import com.dbel.design.system.component.SearchViewModel
import com.rijks.app.data.ArtCollectionRepository
import com.rijks.app.model.ArtObjectOverview
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchArtCollectionViewModel @Inject constructor(
    private val repository: ArtCollectionRepository
) : SearchViewModel<ArtObjectOverview>() {

    override fun doSearch(query: String) = repository.search(query)
}
