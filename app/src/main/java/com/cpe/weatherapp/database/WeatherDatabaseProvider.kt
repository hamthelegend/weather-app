package com.cpe.weatherapp.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideWeatherInfoDao(appDatabase: WeatherDatabase): WeatherInfoDao {
        return appDatabase.weatherInfoDao()
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return WeatherDatabase.getDatabase(appContext)
    }
}