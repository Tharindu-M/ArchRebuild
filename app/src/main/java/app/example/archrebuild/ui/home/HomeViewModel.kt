package app.example.archrebuild.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.archrebuild.data.Task
import app.example.archrebuild.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val isRefreshing = MutableStateFlow(false)
    private val userMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        taskRepository.getTasksStream()
            .catch {
                userMessage.value = "Failed to load tasks"
                emit(emptyList())
            },
        isRefreshing,
        userMessage
    ) { tasks, refreshing, message ->
        HomeUiState(
            isLoading = false,
            isRefreshing = refreshing,
            tasks = tasks,
            userMessage = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HomeUiState(isLoading = true)
    )

    fun refresh() {
        viewModelScope.launch {
            isRefreshing.value = true
            runCatching {
                taskRepository.refresh()
            }.onFailure {
                userMessage.value = "Failed to refresh tasks"
            }
            isRefreshing.value = false
        }
    }

    fun onTaskCheckedChange(task: Task, checked: Boolean) {
        viewModelScope.launch {
            runCatching {
                if (checked) {
                    taskRepository.completeTask(task.id)
                } else {
                    taskRepository.activateTask(task.id)
                }
            }.onFailure {
                userMessage.value = "Failed to update task"
            }
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            runCatching {
                taskRepository.deleteTask(taskId)
            }.onFailure {
                userMessage.value = "Failed to delete task"
            }
        }
    }

    fun clearUserMessage() {
        userMessage.value = null
    }
}
