package com.example.realtimeweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweather.data.Constants
import com.example.realtimeweather.data.NetworkResponse
import com.example.realtimeweather.data.RetrofitInstance
import com.example.realtimeweather.data.model.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(Constants.API_KEY, city)
                if (response.isSuccessful && response.body() != null) {
                    _weatherResult.value = NetworkResponse.Success(response.body()!!)
                } else {
                    _weatherResult.value = NetworkResponse.Error(response.message())
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
