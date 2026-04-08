package app.win365casino.archrebuild.di

import android.content.Context
import androidx.room.Room
import app.win365casino.archrebuild.data.DefaultTaskRepository
import app.win365casino.archrebuild.data.TaskRepository
import app.win365casino.archrebuild.data.local.ToDoDatabase
import app.win365casino.archrebuild.data.network.NetworkDataSource
import app.win365casino.archrebuild.data.network.TaskNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTaskRepository(repository: DefaultTaskRepository): TaskRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(networkDataSource: TaskNetworkDataSource): NetworkDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "Tasks.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: ToDoDatabase) = database.taskDao()

}