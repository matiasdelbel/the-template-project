package com.rijks.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rijks.app.data.ArtCollectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class ArtCollectionViewModel @Inject constructor(
    repository: ArtCollectionRepository
) : ViewModel() {

    val artObjects = repository
        .collections()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
}
