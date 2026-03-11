package com.example.sedekahyuk2_.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties

// Colors
private val TextSlate800 = Color(0xFF1E293B)
private val TextSlate500 = Color(0xFF64748B)
private val TextSlate400 = Color(0xFF94A3B8)
private val BorderSlate200 = Color(0xFFE2E8F0)
private val White = Color.White
private val Blue100 = Color(0xFFDBEAFE) // blue-100
private val Purple100 = Color(0xFFF3E8FF) // purple-100
private val Blue50 = Color(0xFFEFF6FF) // blue-50 (Using similar for Dana bg)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationScreen() {
    var selectedAmount by remember { mutableStateOf("5k") }
    var customAmount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Sedekah Jariyah") }
    var selectedPayment by remember { mutableStateOf("GoPay") }
    var expanded by remember { mutableStateOf(false) }

    val amounts = listOf("5k", "10k", "20k", "50k", "100k", "200k")
    val currentAmount = if (customAmount.isNotEmpty()) customAmount else selectedAmount

    // Parsing amount for footer
    val displayTotal = when {
        customAmount.isNotEmpty() -> "Rp $customAmount"
        selectedAmount.endsWith("k") -> "Rp ${selectedAmount.replace("k", ".000")}"
        else -> "Rp 0"
    }

    Scaffold(
        topBar = {
            // sticky header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor)
                    .padding(top = 48.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
                    .shadow(elevation = 4.dp, spotColor = Color.Black.copy(alpha = 0.1f))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            //.background(Color.Transparent) hover effect simulated
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Sedekah",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp
                    )
                }
            }
        },
        bottomBar = {
            // Footer
            Surface(
                color = White,
                shadowElevation = 16.dp, // heavy shadow
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .padding(bottom = 16.dp) // extra for safe area visual
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Donation",
                            color = TextSlate500,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                        Text(
                            text = displayTotal,
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .shadow(elevation = 10.dp, spotColor = PrimaryColor.copy(alpha = 0.5f), shape = RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                    ) {
                        Text(
                            text = "Pay Now",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    // IOS indicator
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(128.dp)
                            .height(4.dp)
                            .clip(CircleShape)
                            .background(BorderSlate200)
                    )
                }
            }
        },
        containerColor = BackgroundLight
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Donation Amount Selection
            SectionLabel("SELECT AMOUNT")
            
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Determine how to simulate Grid of 3 columns
                // Manually creating rows or using FlowRow would be ideal, but let's use a workaround with Rows for fixed 3 cols
                val rows = amounts.chunked(3)
                rows.forEach { rowAmounts ->
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowAmounts.forEach { amount ->
                            val isSelected = selectedAmount == amount && customAmount.isEmpty()
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(60.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) PrimaryColor else White)
                                    .border(2.dp, if (isSelected) PrimaryColor else BorderSlate200, RoundedCornerShape(8.dp))
                                    .clickable { 
                                        selectedAmount = amount 
                                        customAmount = ""
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = amount,
                                    color = if (isSelected) White else TextSlate800,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Custom Amount Input
            SectionLabel("OTHER AMOUNT")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .border(1.dp, BorderSlate200, RoundedCornerShape(12.dp)), // default border, handling focus visual is harder without interaction source but sticking to design
                contentAlignment = Alignment.CenterStart
            ) {
                 Row(
                     verticalAlignment = Alignment.CenterVertically,
                     modifier = Modifier.padding(horizontal = 16.dp)
                 ) {
                     Text(
                         text = "Rp",
                         color = TextSlate400,
                         fontWeight = FontWeight.Bold
                     )
                     Spacer(modifier = Modifier.width(12.dp))
                     BasicTextField(
                         value = customAmount,
                         onValueChange = { customAmount = it },
                         textStyle = TextStyle(
                             color = TextSlate800,
                             fontSize = 16.sp,
                             fontWeight = FontWeight.Normal
                         ),
                         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                         modifier = Modifier.fillMaxWidth(),
                         cursorBrush = SolidColor(PrimaryColor),
                         decorationBox = { innerTextField ->
                             if (customAmount.isEmpty()) {
                                 Text(
                                    text = "Min. 1.000",
                                    color = TextSlate400.copy(alpha = 0.5f)
                                 )
                             }
                             innerTextField()
                         }
                     )
                 }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Category Dropdown
            SectionLabel("DONATION CATEGORY")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .border(1.dp, BorderSlate200, RoundedCornerShape(12.dp))
                    .clickable { expanded = true }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedCategory,
                        color = TextSlate800,
                        fontSize = 16.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = TextSlate400
                    )
                }
                
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(White).fillMaxWidth(0.85f) // rough width estimation or requires BoxWithConstraints
                ) {
                    listOf("Sedekah Jariyah", "Zakat Maal", "Infaq Bencana", "Wakaf Masjid").forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                selectedCategory = label
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Payment Method
            SectionLabel("PAYMENT METHOD")
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PaymentMethodItem(
                    name = "GoPay",
                    icon = Icons.Filled.AccountBalanceWallet,
                    iconColor = Color(0xFF2563EB), // blue-600
                    iconBg = Blue100,
                    selected = selectedPayment == "GoPay",
                    onSelect = { selectedPayment = "GoPay" }
                )
                PaymentMethodItem(
                    name = "OVO",
                    icon = Icons.Filled.Payment, // Fallback for OVO specific
                    iconColor = Color(0xFF9333EA), // purple-600
                    iconBg = Purple100,
                    selected = selectedPayment == "OVO",
                    onSelect = { selectedPayment = "OVO" }
                )
                PaymentMethodItem(
                    name = "DANA",
                    icon = Icons.Filled.Savings, // Fallback for DANA
                    iconColor = Color(0xFF60A5FA), // blue-400
                    iconBg = Blue50,
                    selected = selectedPayment == "DANA",
                    onSelect = { selectedPayment = "DANA" }
                )
            }
            
            // Padding for bottom scroll
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextSlate500,
        letterSpacing = 1.sp, // tracking-wider
        modifier = Modifier.padding(bottom = 8.dp) // mb-2 / mb-4
    )
}

@Composable
fun PaymentMethodItem(
    name: String,
    icon: ImageVector,
    iconColor: Color,
    iconBg: Color,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) PrimaryColor.copy(alpha = 0.05f) else White)
            .border(2.dp, if (selected) PrimaryColor else BorderSlate200, RoundedCornerShape(12.dp))
            .clickable { onSelect() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = name,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
                color = TextSlate800
            )
        }
        
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = PrimaryColor,
                unselectedColor = TextSlate400
            ),
            modifier = Modifier.size(20.dp)
        )
    }
}


@Preview
@Composable
fun DonationScreenPreview() {
    DonationScreen()
}
