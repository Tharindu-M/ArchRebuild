package app.win365casino.archrebuild.data

import app.win365casino.archrebuild.data.source.local.FakeTaskDao
import app.win365casino.archrebuild.data.source.network.FakeNetworkDataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultTaskRepositoryTest {

    private val task1 = Task(id = "1", title = "Title1", description = "Description1")
    private val task2 = Task(id = "2", title = "Title2", description = "Description2")
    private val task3 = Task(id = "3", title = "Title3", description = "Description3")

    private val newTaskTitle = "Title new"
    private val newTaskDescription = "Description new"
    private val newTask = Task(id = "new", title = newTaskTitle, description = newTaskDescription)
    private val newTasks = listOf(newTask)

    private val networkTasks = listOf(task1, task2).toNetwork()
    private val localTasks = listOf(task3.toLocal())

    // Test dependencies
    private lateinit var localDataSource: FakeTaskDao
    private lateinit var networkDataSource: FakeNetworkDataSource

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    // Class under test
    private lateinit var taskRepository: DefaultTaskRepository

    @Before
    fun createRepository(){
        networkDataSource = FakeNetworkDataSource(networkTasks.toMutableList())
        localDataSource = FakeTaskDao(localTasks)
        // Get a reference to the class under test
        taskRepository = DefaultTaskRepository(
            networkDataSource =  networkDataSource,
            localDataSource = localDataSource,
            dispatcher =  testDispatcher,
            scope = testScope
        )
    }

    @Test
    fun getTasks_emptyRepositoryAndUninitializedCache() = testScope.runTest {
        networkDataSource.tasks = mutableListOf()
        localDataSource.deleteAll()

        assertThat(taskRepository.getTasks().size).isEqualTo(0)
    }

}