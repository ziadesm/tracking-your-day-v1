package com.example.runningapp.di
import android.content.Context
import androidx.room.Room
import com.example.runningapp.db.RunDao
import com.example.runningapp.db.RunningDatabase
import com.example.runningapp.utilities.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, RunningDatabase::class.java, RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()
}