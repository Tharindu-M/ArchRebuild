package app.example.archrebuild.ui.home

import app.example.archrebuild.data.Task

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val userMessage: String? = null
) {
    val isEmpty: Boolean
        get() = tasks.isEmpty() && !isLoading
}
