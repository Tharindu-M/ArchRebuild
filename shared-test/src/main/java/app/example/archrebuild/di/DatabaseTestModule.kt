package app.example.archrebuild.di

import android.content.Context
import androidx.room.Room
import app.example.archrebuild.data.local.ToDoDatabase
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