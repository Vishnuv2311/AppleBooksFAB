package dev.vishnuv.applebooksfab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AutofpsSelect
import androidx.compose.ui.graphics.vector.ImageVector

const val fabPadding = 16

val options = listOf(
    ToggleOption(title = "Contents - 18%", icon = Icons.Default.Menu),
    ToggleOption(title = "Bookmarks & Highlights", icon = Icons.Outlined.AutofpsSelect),
    ToggleOption(title = "Search Book", icon = Icons.Default.Search),
    ToggleOption(title = "Theme & Settings", icon = Icons.Default.Settings),
    ToggleOption(title = "Pets", icon = Icons.Default.Pets),
)

data class ToggleOption(val title: String, val icon: ImageVector)
