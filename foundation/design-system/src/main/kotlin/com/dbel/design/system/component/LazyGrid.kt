package com.dbel.design.system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dbel.design.system.theme.AppTheme

@Composable
fun <T : Any> PagingGrid(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    transformation: @Composable (T) -> Unit,
    loadStateAppend: LazyGridScope.(LazyPagingItems<T>) -> Unit = {},
    loadStateAppendError: LazyGridScope.(LazyPagingItems<T>) -> Unit = {},
    loadStateRefresh: LazyGridScope.(LazyPagingItems<T>) -> Unit = {},
    loadStateRefreshError: LazyGridScope.(LazyPagingItems<T>) -> Unit = {},
) = LazyVerticalGrid(
    columns = GridCells.Fixed(count = 3),
    horizontalArrangement = Arrangement.spacedBy(space = AppTheme.paddings.extraSmall),
    verticalArrangement = Arrangement.spacedBy(space = AppTheme.paddings.extraSmall),
    modifier = modifier
) {
    items(items.itemCount) { index ->
        val item = items[index]!!
        transformation(item)
    }

    refreshLoadState(items, loadStateAppend, loadStateAppendError, loadStateRefresh, loadStateRefreshError)
}

private fun <T : Any> LazyGridScope.refreshLoadState(
    items: LazyPagingItems<T>,
    loadStateAppend: LazyGridScope.(LazyPagingItems<T>) -> Unit,
    loadStateAppendError: LazyGridScope.(LazyPagingItems<T>) -> Unit,
    loadStateRefresh: LazyGridScope.(LazyPagingItems<T>) -> Unit,
    loadStateRefreshError: LazyGridScope.(LazyPagingItems<T>) -> Unit,
) = items.apply {
    when {
        loadState.append is LoadState.Loading -> { loadStateAppend(items) }
        loadState.refresh is LoadState.Loading -> { loadStateRefresh(items) }
        loadState.refresh is LoadState.Error -> { loadStateRefreshError(items) }
        loadState.append is LoadState.Error -> { loadStateAppendError(items) }
    }
}
