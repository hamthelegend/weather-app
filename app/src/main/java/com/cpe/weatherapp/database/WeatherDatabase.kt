package com.cpe.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherInfoEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherInfoDao(): WeatherInfoDao

    companion object {
        @Volatile
        private var Instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WeatherDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}