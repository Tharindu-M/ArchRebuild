package app.win365casino.archrebuild.di

import app.win365casino.archrebuild.data.FakeTaskRepository
import app.win365casino.archrebuild.data.TaskRepository
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