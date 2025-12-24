package com.practicum.playlistmaker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.playlistmaker.R

@Composable
fun SearchScreen(
    onBack: () -> Unit = {}
) {
    // состояние поискового запроса
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.search_title)) },
                navigationIcon = {
                    // кнопка назад, если нужна
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Search, contentDescription = null) // можно заменить иконкой назад
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search_placeholder))
                },
                trailingIcon = {
                    // иконка "х" — очищает поле, только если есть текст
                    IconButton(onClick = {
                        if (query.isNotEmpty()) {
                            query = ""
                        } else {
                            // если нужно — можно обработать нажатие при пустом поле (здесь оставляем пустым)
                        }
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = "Очистить")
                    }
                },
                singleLine = true
            )

            // Здесь можно добавить результат поиска (пока оставляем пустым)
            // search logic will be implemented later
        }
    }
}

@Composable
fun SmallTopAppBar(title: @Composable () -> Unit, navigationIcon: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

