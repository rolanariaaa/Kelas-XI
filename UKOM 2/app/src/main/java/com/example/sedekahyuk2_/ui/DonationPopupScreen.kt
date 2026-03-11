package com.example.sedekahyuk2_.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Toll
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.*
import coil.compose.AsyncImage

// Colors from CSS
private val Green600 = Color(0xFF16A34A)
private val BackgroundDark = Color(0xFF122017)
private val Zinc900 = Color(0xFF18181B)
private val Zinc800 = Color(0xFF27272A)
private val Zinc700 = Color(0xFF3F3F46)
private val Zinc500 = Color(0xFF71717A)
private val Zinc400 = Color(0xFFA1A1AA)
private val Zinc200 = Color(0xFFE4E4E7)
private val Zinc100 = Color(0xFFF4F4F5)
private val White = Color.White
private val Black50 = Color(0x80000000)

@Composable
fun DonationPopupScreen(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    var donorName by remember { mutableStateOf("") }
    var selectedAmount by remember { mutableStateOf("Rp 10.000") }
    var selectedPaymentMethod by remember { mutableStateOf("QRIS") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight) 
    ) {
        // --- Background Content (Mocked & Dimmed) ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                //.graphicsLayer { alpha = 0.4f } // Design says opacity-40, but overlay handles visual dimming better usually. 
                // Using explicit overlay box later. Structurally, this is the "App Content"
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.VolunteerActivism,
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(32.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Zinc200, CircleShape)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Zinc100)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCnBdwE1b8nTfS82Aul5xAVVRNhRgehZvDq_ocCru-QN7tTGUbkPqihWBE-txLuF198MMRZQRrpLKS4KtnK2ZWdEC8Jx02IIRXrP3z0ge9iOqHgBnt9bK_H9kInYlN3mLdmUzmmzrpIE1LtIVlTzaN75-TWm9xz2zkWL1beNsS_JKDH6P8sGRvBFw-F9XQhUiJ4esj0Hx7MYzogE_2T4YA8aPHXVokGVHrfNFvtjNfELRrK6lQ0GjlRx8LVHwzKtK_hDH66Ou5V5Q",
                    contentDescription = "Charity",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Skeleton Text
            Box(modifier = Modifier.fillMaxWidth(0.75f).height(24.dp).background(Zinc200, RoundedCornerShape(4.dp)))
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth().height(16.dp).background(Zinc100, RoundedCornerShape(4.dp)))
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(0.83f).height(16.dp).background(Zinc100, RoundedCornerShape(4.dp)))
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Skeleton Grid
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier.weight(1f).height(96.dp).background(Zinc100, RoundedCornerShape(8.dp)))
                Box(modifier = Modifier.weight(1f).height(96.dp).background(Zinc100, RoundedCornerShape(8.dp)))
            }
        }
        
        // --- Overlay / Backdrop ---
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black50)
        )
        
        // --- Bottom Sheet ---
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 30.dp, spotColor = Color(0x1F000000))
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(White)
                    .padding(top = 12.dp, bottom = 40.dp) // pb-10 + safe area approx
            ) {
                // Drag Handle
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(6.dp)
                            .background(Zinc200, CircleShape)
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    // Title
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Pilih Nominal Donasi",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Zinc900
                        )
                        Text(
                            text = "Setiap kebaikan Anda sangat berarti",
                            fontSize = 14.sp,
                            color = Zinc500,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Donor Name Input
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                         Text(
                            text = "NAMA DONATUR",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Zinc500,
                            letterSpacing = 1.sp
                        ) // uppercase tracking-wider
                        
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            // Using a basic TF styling to match design exactly
                             TextField(
                                value = donorName,
                                onValueChange = { donorName = it },
                                placeholder = { Text("Hamba Allah", color = Zinc400) },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = null,
                                        tint = Zinc400,
                                        modifier = Modifier.size(20.dp) // material icons text-sm approx
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = BackgroundLight,
                                    unfocusedContainerColor = BackgroundLight,
                                    disabledContainerColor = BackgroundLight,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    
                     Spacer(modifier = Modifier.height(12.dp))
                     
                     // Amount Grid
                     Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                         Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                             DonationOptionItem(
                                 modifier = Modifier.weight(1f),
                                 amount = "Rp 5.000",
                                 icon = Icons.Filled.Toll,
                                 isSelected = selectedAmount == "Rp 5.000",
                                 onClick = { selectedAmount = "Rp 5.000" }
                             )
                             DonationOptionItem(
                                 modifier = Modifier.weight(1f),
                                 amount = "Rp 10.000",
                                 icon = Icons.Filled.Savings,
                                 isSelected = selectedAmount == "Rp 10.000",
                                 onClick = { selectedAmount = "Rp 10.000" }
                             )
                         }
                         Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                             DonationOptionItem(
                                 modifier = Modifier.weight(1f),
                                 amount = "Rp 20.000",
                                 icon = Icons.Filled.Diamond,
                                 isSelected = selectedAmount == "Rp 20.000",
                                 onClick = { selectedAmount = "Rp 20.000" }
                             )
                             DonationOptionItem(
                                 modifier = Modifier.weight(1f),
                                 amount = "Rp 50.000",
                                 icon = Icons.Filled.WorkspacePremium,
                                 isSelected = selectedAmount == "Rp 50.000",
                                 onClick = { selectedAmount = "Rp 50.000" }
                             )
                         }
                     }
                     
                     // Custom Amount
                     Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                     ) {
                         Icon(
                             imageVector = Icons.Filled.Edit,
                             contentDescription = null,
                             tint = PrimaryColor,
                             modifier = Modifier.size(16.dp)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                         Text(
                             text = "Nominal Lainnya",
                             fontSize = 14.sp,
                             fontWeight = FontWeight.SemiBold,
                             color = PrimaryColor
                         )
                     }
                     
                     Spacer(modifier = Modifier.height(16.dp))
                     
                     // Payment Method Selection
                     Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                         Text(
                            text = "METODE PEMBAYARAN",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Zinc500,
                            letterSpacing = 1.sp
                        )
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                        ) {
                            PaymentMethodItem(
                                name = "QRIS",
                                icon = Icons.Rounded.QrCodeScanner,
                                isSelected = selectedPaymentMethod == "QRIS",
                                onClick = { selectedPaymentMethod = "QRIS" }
                            )
                            PaymentMethodItem(
                                name = "GoPay",
                                icon = Icons.Filled.Payment,
                                isSelected = selectedPaymentMethod == "GoPay",
                                onClick = { selectedPaymentMethod = "GoPay" }
                            )
                            PaymentMethodItem(
                                name = "Dana",
                                icon = Icons.Filled.AccountBalanceWallet,
                                isSelected = selectedPaymentMethod == "Dana",
                                onClick = { selectedPaymentMethod = "Dana" }
                            )
                             PaymentMethodItem(
                                name = "OVO",
                                icon = Icons.Filled.Toll,
                                isSelected = selectedPaymentMethod == "OVO",
                                onClick = { selectedPaymentMethod = "OVO" }
                            )
                        }
                     }
                     
                     Spacer(modifier = Modifier.height(24.dp))
                     
                     // Actions
                     Button(
                        onClick = onConfirm,
                         modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .shadow(elevation = 10.dp, spotColor = PrimaryColor.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                         Text(
                            text = "Lanjut Pembayaran",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    }
                    
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Batal",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Zinc500
                        )
                    }
                }
                
                // iOS Home indicator
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.width(128.dp).height(4.dp).background(Zinc200, CircleShape))
                }
            }
        }
    }
}

@Composable
fun DonationOptionItem(
    modifier: Modifier = Modifier,
    amount: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit // Added onClick
) {
    val borderColor = if (isSelected) PrimaryColor else Zinc100
    val borderSize = if (isSelected) 2.dp else 1.dp
    val bgColor = if (isSelected) PrimaryColor.copy(alpha = 0.05f) else BackgroundLight
    val iconColor = if (isSelected) PrimaryColor else Zinc400
    
    // Hover state is difficult to replicate exactly in touch, but visually checked
    Box(
        modifier = modifier
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(borderSize, borderColor, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick) // Use onClick
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
             Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp).padding(bottom = 8.dp)
            )
            Text(
                text = amount,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Zinc900
            )
        }
    }
}

@Composable
fun PaymentMethodItem(
    name: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) PrimaryColor else Zinc100
    val borderSize = if (isSelected) 2.dp else 1.dp
    val bgColor = if (isSelected) PrimaryColor.copy(alpha = 0.05f) else Color.Transparent

    Box(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(borderSize, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if(isSelected) PrimaryColor else Zinc500,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if(isSelected) Zinc900 else Zinc500,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun DonationPopupScreenPreview() {
    DonationPopupScreen()
}