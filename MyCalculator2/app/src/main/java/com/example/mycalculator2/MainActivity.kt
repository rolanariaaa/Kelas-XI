package com.example.mycalculator2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalculator2.ui.theme.MyCalculator2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalculator2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    // State management for input numbers
    val num1 = remember { mutableStateOf("0") }
    val num2 = remember { mutableStateOf("0") }
    var resultState by remember { mutableStateOf("Result: 0") }
    
    // Context for Toast messages
    val context = LocalContext.current
    
    // Main container column
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // First number input field
        OutlinedTextField(
            value = num1.value,
            onValueChange = { num1.value = it },
            label = { Text("First Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        
        // Second number input field
        OutlinedTextField(
            value = num2.value,
            onValueChange = { num2.value = it },
            label = { Text("Second Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        
        // Row for calculator operation buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Addition button
            Button(
                onClick = {
                    val n1 = num1.value.toIntOrNull() ?: 0
                    val n2 = num2.value.toIntOrNull() ?: 0
                    val result = n1 + n2
                    resultState = "Result: $result"
                    Toast.makeText(context, "Result is: $result", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("ADD")
            }
            
            // Subtraction button
            Button(
                onClick = {
                    val n1 = num1.value.toIntOrNull() ?: 0
                    val n2 = num2.value.toIntOrNull() ?: 0
                    val result = n1 - n2
                    resultState = "Result: $result"
                    Toast.makeText(context, "Result is: $result", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("SUB")
            }
            
            // Multiplication button
            Button(
                onClick = {
                    val n1 = num1.value.toIntOrNull() ?: 0
                    val n2 = num2.value.toIntOrNull() ?: 0
                    val result = n1 * n2
                    resultState = "Result: $result"
                    Toast.makeText(context, "Result is: $result", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("MUL")
            }
            
            // Division button
            Button(
                onClick = {
                    val n1 = num1.value.toIntOrNull() ?: 0
                    val n2 = num2.value.toIntOrNull() ?: 0
                    
                    // Check for division by zero
                    if (n2 == 0) {
                        Toast.makeText(context, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    } else {
                        val result = n1 / n2
                        resultState = "Result: $result"
                        Toast.makeText(context, "Result is: $result", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("DIV")
            }
        }
        
        // Display result (bonus feature)
        Text(
            text = resultState,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    MyCalculator2Theme {
        CalculatorScreen()
    }
}