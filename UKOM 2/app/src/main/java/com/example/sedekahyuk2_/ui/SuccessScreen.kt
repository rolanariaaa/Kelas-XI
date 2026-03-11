package com.example.sedekahyuk2_.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

import androidx.compose.material.icons.rounded.VolunteerActivism

// Colors for Success Screen
private val AccentYellow = Color(0xFFFACC15)
private val Neutral50 = Color(0xFFFAFAFA)
private val Neutral100 = Color(0xFFF5F5F5)
private val Neutral200 = Color(0xFFE5E5E5)
private val Neutral300 = Color(0xFFD4D4D4)
private val Neutral500 = Color(0xFF737373)
private val Neutral600 = Color(0xFF525252)
private val Neutral700 = Color(0xFF404040)
private val Neutral800 = Color(0xFF262626)
private val Neutral900 = Color(0xFF171717)
private val TextSlate800 = Color(0xFF1E293B)

@Composable
fun SuccessScreen(onNavigate: (Screen) -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    val scale = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        isVisible = true
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = BackgroundLight,
            bottomBar = {
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(animationSpec = tween(1000, 300))
                ) {
                    Column(
                         modifier = Modifier
                             .fillMaxWidth()
                             .background(Color.White)
                             .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                         Button(
                            onClick = { onNavigate(Screen.TransactionHistory) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .shadow(elevation = 10.dp, spotColor = PrimaryColor.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                        ) {
                            Text(
                                text = "Lihat Riwayat",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        OutlinedButton(
                            onClick = { onNavigate(Screen.Home) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Neutral600),
                            border = androidx.compose.foundation.BorderStroke(2.dp, Neutral200)
                        ) {
                            Text(
                                text = "Tutup",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // iOS Home Indicator Spacer
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(128.dp)
                                .height(5.dp) // Adjusted height slightly
                                .clip(CircleShape)
                                .background(Neutral200)
                        )
                        Spacer(modifier = Modifier.height(8.dp)) // Extra padding
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(44.dp)) // Status bar spacer
                
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500)) +
                            slideInVertically(initialOffsetY = { 50 }, animationSpec = tween(durationMillis = 500))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(32.dp)) // Top Scroll Area padding
                        
                        // Success Icon
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .scale(scale.value)
                                .background(PrimaryColor.copy(alpha = 0.1f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Success",
                                tint = PrimaryColor,
                                modifier = Modifier.size(60.dp) // text-6xl approx
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Title
                        Text(
                            text = "Transaksi Berhasil!",
                            fontSize = 24.sp, // 2xl
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Amount
                        Text(
                            text = "Rp 10.000,00", // Updated to match default selection
                            fontSize = 36.sp, // 4xl
                            fontWeight = FontWeight.Bold,
                            color = Neutral800
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Reward Badge
                        Row(
                            modifier = Modifier
                                .background(AccentYellow, CircleShape)
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                                .shadow(1.dp, CircleShape),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Stars,
                                contentDescription = null,
                                tint = Neutral900,
                                modifier = Modifier.size(18.dp) // text-sm
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+50 Poin Berkah",
                                fontSize = 14.sp, // text-sm
                                fontWeight = FontWeight.SemiBold,
                                color = Neutral900
                            )
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        // Description
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Neutral700)) {
                                    append("Alhamdulillah! ")
                                }
                                append("Sedekah Anda melalui SedekahYuk telah kami terima dan akan segera disalurkan.")
                            },
                            fontSize = 16.sp,
                            color = Neutral500,
                            lineHeight = 24.sp, // relaxed
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.width(320.dp) // max-w-xs approx
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        // Status Card
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Neutral50, RoundedCornerShape(8.dp))
                                .border(1.dp, Neutral100, RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(PrimaryColor.copy(alpha = 0.2f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountBalanceWallet,
                                        contentDescription = null,
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Sisa Saldo",
                                        fontSize = 12.sp,
                                        color = Neutral500
                                    )
                                    Text(
                                        text = "Rp 450.500,00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Neutral800
                                    )
                                }
                            }
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = null,
                                tint = Neutral300
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
        
        // Confetti Overlay
        ConfettiView()
    }
}


@Composable
fun ConfettiView() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Time"
    )

    // Generate random confetti data with x, y, color, speed, and size
    val confettis = remember {
        List(100) {
            Confetti(
                x = Random.nextFloat(),
                y = Random.nextFloat() * -1f, // Start scattered above
                color = if (Random.nextBoolean()) PrimaryColor else AccentYellow,
                radius = Random.nextFloat() * 8f + 4f,
                speed = Random.nextFloat() * 0.5f + 0.2f
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val swayAmount = 20f

        confettis.forEach { confetti ->
             // Simple falling logic
             // y starts at random negative, moves down with time * speed multiplier
             val rawY = confetti.y + (time * 2.5f) // speed multiplier
             val animatedY = rawY.rem(1.5f) - 0.2f
             
            val sway = sin((time * 6.28f) + (confetti.x * 10f)) * swayAmount
             
            if (animatedY in -0.1f..1.1f) {
                drawCircle(
                    color = confetti.color,
                    radius = confetti.radius,
                    center = Offset(
                        x = (confetti.x * canvasWidth + sway).coerceIn(0f, canvasWidth),
                        y = animatedY * canvasHeight
                    ),
                    alpha = (1f - (animatedY - 0.7f).coerceAtLeast(0f) * 3f).coerceIn(0f, 1f)
                )
            }
        }
    }
}


data class Confetti(
    val x: Float,
    val y: Float,
    val color: Color,
    val radius: Float,
    val speed: Float
)


// Helping with gradients
fun Brush.Companion.brushedGradient(colors: List<Color>): Brush {
    // approximating 'bg-gradient-to-br'
    return linearGradient(
         colors = colors,
         start = Offset.Zero,
         end = Offset.Infinite
    )
}

@Preview
@Composable
fun SuccessScreenPreview() {
    SuccessScreen(onNavigate = {})
}
