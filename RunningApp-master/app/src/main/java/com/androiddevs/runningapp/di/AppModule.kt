package com.androiddevs.runningapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.androiddevs.runningapp.db.RunDao
import com.androiddevs.runningapp.db.RunningDatabase
import com.androiddevs.runningapp.other.Constants.Companion.DATABASE_NAME
import com.androiddevs.runningapp.other.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.androiddevs.runningapp.other.Constants.Companion.KEY_NAME
import com.androiddevs.runningapp.other.Constants.Companion.KEY_WEIGHT
import com.androiddevs.runningapp.other.Constants.Companion.SHARED_PREFERENCES_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): RunningDatabase {
        return Room.databaseBuilder(app, RunningDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase): RunDao {
        return db.getRunDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) =
        sharedPreferences.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): BlogRetrofit{
        return retrofit.build().create(BlogRetrofit::class.java)
    }

}