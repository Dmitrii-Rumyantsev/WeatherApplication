package com.example.weather.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.WeatherCity
import com.example.weather.domain.request.WeatherRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    private val _weatherState = MutableStateFlow<WeatherCity?>(null)
    val weatherState: StateFlow<WeatherCity?> = _weatherState

    private val weatherRequest = WeatherRequest()

    suspend fun fetchWeatherData() {
         viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherData = weatherRequest.doKtorRequest()
                _weatherState.value = weatherData
                Log.d("Info", weatherState.value!!.city)
            } catch (e: Exception) {
                // Обработка ошибок при выполнении запроса
                e.printStackTrace()
            }
        }
    }
}