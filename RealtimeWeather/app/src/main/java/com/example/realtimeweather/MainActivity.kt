package com.example.realtimeweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimeweather.data.NetworkResponse
import com.example.realtimeweather.data.model.WeatherModel
import com.example.realtimeweather.ui.theme.RealtimeWeatherTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Buat ViewModel melalui ViewModelProvider dan pass ke composable
        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setContent {
            RealtimeWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        WeatherPage(viewModel = weatherViewModel)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherResult.observeAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        SearchBar(onSearch = { city ->
            viewModel.getData(city)
        })

        Spacer(modifier = Modifier.height(16.dp))

        when (weatherState) {
            is NetworkResponse.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is NetworkResponse.Error -> {
                val msg = (weatherState as NetworkResponse.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: $msg", color = Color.Red)
                }
            }
            is NetworkResponse.Success -> {
                val data = (weatherState as NetworkResponse.Success<WeatherModel>).data
                WeatherDetails(weather = data)
            }
            else -> {
                // Initial state - optional prompt
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Masukkan nama kota dan tekan cari", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var city by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            modifier = Modifier.weight(1f),
            label = { Text(text = "Kota")
            }
        )

        IconButton(onClick = {
            if (city.isNotBlank()) {
                onSearch(city.trim())
                keyboardController?.hide()
            }
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}

@Composable
fun WeatherDetails(weather: WeatherModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location")
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = weather.location.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = weather.location.country, fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Temperature and icon
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${weather.current.temp_c}\u00B0C", fontSize = 56.sp, fontWeight = FontWeight.Bold)
                Text(text = weather.current.condition.text, fontSize = 18.sp)
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https:${weather.current.condition.icon}")
                    .crossfade(true)
                    .build(),
                contentDescription = weather.current.condition.text,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .padding(12.dp)) {
                // Two columns per row using Row + Column pairs
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    WeatherKeyValue(key = "Humidity", value = "${weather.current.humidity}%", modifier = Modifier.weight(1f))
                    WeatherKeyValue(key = "Wind", value = "${weather.current.wind_kph} kph", modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    WeatherKeyValue(key = "UV", value = "${weather.current.uv}", modifier = Modifier.weight(1f))
                    WeatherKeyValue(key = "Precip.", value = "${weather.current.precip_mm} mm", modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Local time: ${weather.location.localtime}", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun WeatherKeyValue(key: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = value, fontWeight = FontWeight.Bold)
        Text(text = key, fontSize = 12.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    RealtimeWeatherTheme {
        // Dummy preview
        val sample = WeatherModel(
            location = com.example.realtimeweather.data.model.Location("Jakarta", "Indonesia", "2025-10-07 12:00"),
            current = com.example.realtimeweather.data.model.Current(28.5f, com.example.realtimeweather.data.model.Condition("Sunny", "//cdn.weatherapi.com/weather/64x64/day/113.png"), 70, 12.3f, 5.0f, 0.0f)
        )
        WeatherDetails(weather = sample)
    }
}