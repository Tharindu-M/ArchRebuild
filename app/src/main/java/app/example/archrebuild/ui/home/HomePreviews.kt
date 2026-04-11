package app.example.archrebuild.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.example.archrebuild.ui.theme.ArchRebuildTheme

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ArchRebuildTheme {
        HomeScreenPreviewContent()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenEmptyPreview() {
    ArchRebuildTheme {
        HomeScreen(
            uiState = HomeUiState(isLoading = false, tasks = emptyList()),
            onTaskCheckedChange = { _, _ -> },
            onDeleteTask = {}
        )
    }
}
