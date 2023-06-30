package com.cpe.weatherapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM weather ORDER BY id DESC")
    fun getAll(): Flow<List<WeatherInfoEntity>>

    @Query("SELECT * FROM weather WHERE id = :id")
    fun get(id: Long): WeatherInfoEntity

    @Insert
    fun insert(weatherInfo: WeatherInfoEntity)

    @Delete
    fun delete(weatherInfo: WeatherInfoEntity)

}