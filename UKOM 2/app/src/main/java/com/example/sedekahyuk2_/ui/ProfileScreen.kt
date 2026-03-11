package com.example.sedekahyuk2_.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen(onNavigate: (Screen) -> Unit) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Profile Screen")
                Button(
                    onClick = { onNavigate(Screen.Home) },
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Text(text = "Back to Home")
                }
            }
        }
    )
}
