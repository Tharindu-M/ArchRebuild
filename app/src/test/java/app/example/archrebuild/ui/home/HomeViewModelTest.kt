package app.example.archrebuild.ui.home

import app.example.archrebuild.MainCoroutineRule
import app.example.archrebuild.data.FakeTaskRepository
import app.example.archrebuild.data.Task
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var taskRepository: FakeTaskRepository
    private lateinit var viewModel: HomeViewModel

    private val activeTask = Task(id = "1", title = "Title1", description = "Description1")
    private val completedTask = Task(id = "2", title = "Title2", description = "Description2", isCompleted = true)

    @Before
    fun setup() {
        taskRepository = FakeTaskRepository()
        viewModel = HomeViewModel(taskRepository)
    }

    @Test
    fun uiState_whenRepositoryHasNoTasks_isEmpty() = runTest {
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isEmpty).isTrue()
    }

    @Test
    fun uiState_whenRepositoryHasTasks_emitsContent() = runTest {
        taskRepository.addTasks(activeTask, completedTask)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state.tasks).hasSize(2)
        assertThat(state.isEmpty).isFalse()
    }

    @Test
    fun onTaskCheckedChange_marksTaskCompleted() = runTest {
        taskRepository.addTasks(activeTask)
        advanceUntilIdle()

        viewModel.onTaskCheckedChange(activeTask, true)
        advanceUntilIdle()

        val updatedTask = taskRepository.getTask(activeTask.id)
        assertThat(updatedTask?.isCompleted).isTrue()
    }

    @Test
    fun deleteTask_removesTask() = runTest {
        taskRepository.addTasks(activeTask)
        advanceUntilIdle()

        viewModel.deleteTask(activeTask.id)
        advanceUntilIdle()

        val task = taskRepository.getTask(activeTask.id)
        assertThat(task).isNull()
    }

    @Test
    fun refresh_callsRepositoryRefreshAndClearsRefreshingState() = runTest {
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state.isRefreshing).isFalse()
    }
}
