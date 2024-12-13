package com.nasa.app.ui

import com.dbel.design.system.component.SearchViewModel
import com.nasa.app.data.ImageCollectionRepository
import com.nasa.app.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchImageCollectionViewModel @Inject constructor(
    private val repository: ImageCollectionRepository
) : SearchViewModel<Image>() {

    override fun doSearch(query: String) = repository.searchImages(query)
}
