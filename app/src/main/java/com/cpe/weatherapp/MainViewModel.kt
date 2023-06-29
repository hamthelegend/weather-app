package com.cpe.weatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cpe.weatherapp.database.WeatherInfoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val dao: WeatherInfoDao,
) : AndroidViewModel(application) {



}