package app.example.archrebuild.di

import app.example.archrebuild.data.FakeTaskRepository
import app.example.archrebuild.data.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object RepositoryTestModule {

    @Singleton
    @Provides
    fun provideTaskRepository(): TaskRepository {
        return FakeTaskRepository()
    }

}