package app.example.archrebuild.data.source.network

import app.example.archrebuild.data.network.NetworkDataSource
import app.example.archrebuild.data.network.NetworkTask

class FakeNetworkDataSource(
    var tasks: List<NetworkTask>? = mutableListOf()
) : NetworkDataSource {

    override suspend fun loadTasks(): List<NetworkTask> = tasks ?: throw Exception("Task list is null")

    override suspend fun saveTasks(tasks: List<NetworkTask>) {
        this.tasks = tasks.toMutableList()
    }

}