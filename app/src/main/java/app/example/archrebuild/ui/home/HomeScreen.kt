package app.example.archrebuild.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import app.example.archrebuild.data.Task

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onDeleteTask: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Loading tasks...",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        uiState.isEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No tasks yet",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Your task list is empty. Pull to refresh.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = uiState.tasks,
                    key = { it.id }
                ) { task ->
                    TaskRow(
                        task = task,
                        onTaskCheckedChange = onTaskCheckedChange,
                        onDeleteTask = onDeleteTask,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskRow(
    task: Task,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onDeleteTask: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { checked -> onTaskCheckedChange(task, checked) }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = task.titleForList,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                if (task.description.isNotEmpty()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            IconButton(onClick = { onDeleteTask(task.id) }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete task"
                )
            }
        }
    }
}

@Composable
fun HomeScreenPreviewContent(modifier: Modifier = Modifier) {
    HomeScreen(
        uiState = HomeUiState(
            isLoading = false,
            tasks = listOf(
                Task(id = "1", title = "Design home screen", description = "Create list + actions"),
                Task(id = "2", title = "Hook repository", description = "Collect task stream", isCompleted = true)
            )
        ),
        onTaskCheckedChange = { _, _ -> },
        onDeleteTask = {},
        modifier = modifier
    )
}
