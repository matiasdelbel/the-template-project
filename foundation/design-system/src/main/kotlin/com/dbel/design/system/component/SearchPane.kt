package com.dbel.design.system.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPane(
    query: String,
    placeholder: String,
    results: @Composable ColumnScope.() -> Unit,
    onQueryChange: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded = remember { mutableStateOf(value = false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { expanded.value = false },
                expanded = expanded.value,
                enabled = false,
                onExpandedChange = { expanded.value = it },
                placeholder = { Text(placeholder) },
                leadingIcon = {
                    if (expanded.value) IconButton(onClick = { expanded.value = false }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                    else Icon(Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (query.isNotEmpty()) IconButton(onClick = { onQueryChange("") }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
        expanded = expanded.value,
        onExpandedChange = { expanded.value = it },
        modifier = modifier,
        content = results
    )
}
