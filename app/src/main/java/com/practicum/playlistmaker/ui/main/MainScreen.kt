package com.practicum.playlistmaker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.width(360.dp).padding(top = 48.dp)) {
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFF2F80ED), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Playlist maker",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 520.dp),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                tonalElevation = 2.dp,
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(vertical = 20.dp)) {
                    MenuItem(icon = Icons.Default.Search, text = "Поиск", onClick = onSearchClick)
                    Divider()
                    MenuItem(icon = Icons.Default.QueueMusic, text = "Плейлисты", onClick = onPlaylistsClick)
                    Divider()
                    MenuItem(icon = Icons.Default.FavoriteBorder, text = "Избранное", onClick = onFavoritesClick)
                    Divider()
                    MenuItem(icon = Icons.Default.Settings, text = "Настройки", onClick = onSettingsClick)
                }
            }
        }
    }
}

@Composable
fun MenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 18.sp, modifier = Modifier.weight(1f), color = Color(0xFF111111))
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "open", modifier = Modifier.size(28.dp))
    }
}

