package app.win365casino.archrebuild.di

import android.content.Context
import androidx.room.Room
import app.win365casino.archrebuild.data.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object DatabaseTestModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

}