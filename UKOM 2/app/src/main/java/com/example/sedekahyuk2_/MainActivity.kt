package com.example.sedekahyuk2_

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import com.example.sedekahyuk2_.ui.SplashScreen
import com.example.sedekahyuk2_.ui.HomeScreen
import com.example.sedekahyuk2_.ui.TransactionHistoryScreen
import com.example.sedekahyuk2_.ui.ProfileScreen
import com.example.sedekahyuk2_.ui.DonationPopupScreen
import com.example.sedekahyuk2_.ui.SuccessScreen
import com.example.sedekahyuk2_.ui.Screen
import com.example.sedekahyuk2_.ui.theme.SedekahYUK2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SedekahYUK2Theme {
                var currentScreen by remember { mutableStateOf(Screen.Splash) }

                LaunchedEffect(Unit) {
                    delay(3000) // 3 seconds delay
                    currentScreen = Screen.Home
                }

                when (currentScreen) {
                    Screen.Splash -> SplashScreen()
                    Screen.Home -> HomeScreen(onNavigate = { screen -> currentScreen = screen })
                    Screen.TransactionHistory -> TransactionHistoryScreen(onNavigate = { screen -> currentScreen = screen })
                    Screen.Profile -> ProfileScreen(onNavigate = { screen -> currentScreen = screen })
                    Screen.DonationPopup -> DonationPopupScreen(
                        onDismiss = { currentScreen = Screen.Home },
                        onConfirm = { currentScreen = Screen.Success }
                    )
                    Screen.Success -> SuccessScreen(onNavigate = { screen -> currentScreen = screen })
                    else -> HomeScreen(onNavigate = { screen -> currentScreen = screen }) // Fallback
                }
            }
        }
    }
}