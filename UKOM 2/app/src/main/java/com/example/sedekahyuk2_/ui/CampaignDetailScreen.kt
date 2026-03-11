package com.example.sedekahyuk2_.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// Colors
private val DarkBaseColor = Color(0xFF0A0F0C)
private val PrimaryGreen = Color(0xFF22C55E)
private val NeonGreen = Color(0xFF4ADE80)
private val GlassWhite = Color.White.copy(alpha = 0.1f)
private val GlassBorder = Color.White.copy(alpha = 0.2f)
private val TextWhite = Color(0xFFF1F5F9) // slate-100
private val TextSlate400 = Color(0xFF94A3B8) // slate-400

@Composable
fun CampaignDetailScreen() {
    Scaffold(
        containerColor = DarkBaseColor,
        bottomBar = { CampaignBottomBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    CampaignHeader()
                }
                item {
                    CampaignContent()
                }
                // Spacer for scrolling past the bottom bar visually if needed, 
                // though Scaffold padding handles the interaction area.
                // Adding extra safety spacer.
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            // Top Buttons positioned absolutely over the ScrollView content (similar to the HTML)
            TopBarOverlay()
        }
    }
}

@Composable
fun TopBarOverlay() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 24.dp, end = 24.dp), // Adjust top for status bar
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GlassIconButton(
            icon = { Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Back", tint = Color.White, modifier = Modifier.size(20.dp)) }
        )
        GlassIconButton(
            icon = { Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.White, modifier = Modifier.size(20.dp)) }
        )
    }
}

@Composable
fun GlassIconButton(icon: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(GlassWhite)
            .border(1.dp, GlassBorder, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

@Composable
fun CampaignHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) {
        // Background Image
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBjDIaXkOgJ5NAy-vQ9erqIm7VGU5nxrNJ4X65I0T6c3_1sxYIKBBUQIMYKbVOnC52x8k8Dj_zLN2JpjbEabfE31jXy_dL46Yf-AzHPYxjoT1M7x7LULgGxt3dD5_XWRmpfQJ7KZvwYOW7hozgN9eScA8cZ78yOMKR3qslNfKMw7hY0PQHYF1fQgWB8oo_W2odsbow4QxMErHonV335CnDAk0T6ATTdO6zs6FOdhXLeOofJCoeRjbuMVZNRB-xyqZXxxJb9lug_hw",
            contentDescription = "Bencana Alam Relief",
            modifier = Modifier.fillMaxSize().scale(1.1f),
            contentScale = ContentScale.Crop
        )
        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Transparent,
                            DarkBaseColor
                        )
                    )
                )
        )

        // Floating Progress Card
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp)
                .padding(bottom = 40.dp) // Lifted up to overlap
                .fillMaxWidth()
        ) {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(40.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = "TERKUMPUL",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White.copy(alpha = 0.6f),
                                letterSpacing = 1.5.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Rp 350.0M",
                                fontSize = 30.sp, // ~3xl
                                fontWeight = FontWeight.ExtraBold,
                                color = TextWhite,
                                letterSpacing = (-1).sp
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "TARGET",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White.copy(alpha = 0.6f),
                                letterSpacing = 1.5.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Rp 500.0M",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }

                    // Progress Bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .fillMaxSize()
                                .background(PrimaryGreen)
                                .shadowGlow(PrimaryGreen)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Donors and Time
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Avatar Stack
                            Box(modifier = Modifier.width(56.dp).height(24.dp)) {
                                val border = 2.dp
                                DonatorAvatar(Color(0xFF94A3B8), 0, border)
                                DonatorAvatar(Color(0xFF64748B), 16, border)
                                DonatorAvatar(Color(0xFF475569), 32, border)
                            }
                            Text(
                                text = "1,240+ Donatur",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                        
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Timer, 
                                contentDescription = null, 
                                tint = PrimaryGreen,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "12 Hari Lagi",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryGreen
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DonatorAvatar(color: Color, offset: Int, border: Dp) {
    Box(
        modifier = Modifier
            .offset(x = offset.dp)
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
            .border(border, GlassBorder, CircleShape)
    )
}

@Composable
fun CampaignContent() {
    Column(
        modifier = Modifier
            .offset(y = (-16).dp) // Overlap upwards
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(DarkBaseColor)
            .padding(horizontal = 24.dp)
            .padding(bottom = 120.dp) // Space for bottom bar
    ) {
        // Drag Handle
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
                .width(48.dp)
                .height(4.dp)
                .background(Color.White.copy(alpha = 0.1f), CircleShape)
        )

        // Tag
        Row(
            modifier = Modifier
                .background(PrimaryGreen.copy(alpha = 0.1f), CircleShape)
                .border(1.dp, PrimaryGreen.copy(alpha = 0.2f), CircleShape)
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Emergency,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "BENCANA ALAM",
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryGreen,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Bantu Korban Banjir Bandang di Wilayah Jawa Barat",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextWhite,
            lineHeight = 32.sp,
            letterSpacing = (-0.5).sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Organizer Card
        GlassCard(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().clickableWithScaling {}
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBLf2zpf45aDiczrB17qXaNLdiVpnW8TOEMeRNsxJsTdKNWsKdfaZg1urSMercORMiyAjn77I5MRCEozs77B_7n9WAqTfwJ2g97HvOKWF7LyoJ1Zt9qODxGrPNr5OssxBjwbBunI_XWy-8lmxo5Aga1OY2m3wjLa3OSWxUATcd860gQV2WPItU8wCHnlPmBI6w0MBRjXY-qTQJTCruLfFZvaRWiyjINfDTwjMtcrCftEFG_ZObye3LLewvYn5TMDg7lCQwtgUXZ-g",
                        contentDescription = "Organizer",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(2.dp, PrimaryGreen.copy(alpha = 0.3f), CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 4.dp, y = 4.dp)
                            .background(PrimaryGreen, CircleShape)
                            .border(2.dp, DarkBaseColor, CircleShape)
                            .padding(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Verified,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Yayasan Peduli Sesama",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = "Penyelenggara Terverifikasi",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSlate400,
                        letterSpacing = 0.5.sp
                    )
                }

                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = PrimaryGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // About Campaign
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(PrimaryGreen, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Tentang Kampanye",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Curah hujan yang ekstrim selama tiga hari terakhir telah menyebabkan banjir bandang di beberapa titik di Jawa Barat. Ratusan rumah warga terendam dan ribuan orang terpaksa mengungsi ke tempat yang lebih aman.",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = TextSlate400,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Usage Plan Card
        Box(modifier = Modifier.fillMaxWidth()) {
             // Decorative Glow
             Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-10).dp)
                    .size(96.dp)
                    .background(PrimaryGreen.copy(alpha = 0.2f))
                    .blurEffect()
            )

            GlassCard(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Analytics,
                            contentDescription = null,
                            tint = PrimaryGreen
                        )
                         Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rencana Penggunaan Dana",
                            fontSize = 16.sp, // approximate h4
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    UsageItem("Pembelian 1.000 paket sembako darurat")
                    UsageItem("Penyediaan obat-obatan & vitamin medis")
                    UsageItem("Fasilitas dapur umum & air bersih")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Mari ulurkan tangan kita untuk membantu saudara-saudara kita yang sedang tertimpa musibah. Setiap rupiah yang Anda donasikan sangat berarti bagi mereka.",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = TextSlate400,
            lineHeight = 24.sp
        )
    }
}

@Composable
fun UsageItem(text: String) {
    Row(
        modifier = Modifier.padding(bottom = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .size(6.dp)
                .background(PrimaryGreen, CircleShape)
                .shadowGlow(PrimaryGreen, 8.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = TextWhite
        )
    }
}

@Composable
fun CampaignBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bookmark Button
            GlassCard(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .size(64.dp)
                    .clickableWithScaling {}
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.Bookmark,
                        contentDescription = "Bookmark",
                        tint = Color.White
                    )
                }
            }

            // Donate Button with Pulse Animation
            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0.98f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "scale"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .scale(scale)
                    .shadowCustom(
                        color = PrimaryGreen.copy(alpha = 0.4f),
                        blurRadius = 30.dp,
                        offsetY = 10.dp
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(PrimaryGreen, Color(0xFF34D399)) // emerald 400
                        )
                    )
                    .clickableWithScaling {}
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Donasi Sekarang",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                }
            }
        }
    }
}


// --- Helper Components & Modifiers ---

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(0.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(GlassWhite)
            .border(1.dp, GlassBorder, shape)
    ) {
        content()
    }
}

fun Modifier.shadowGlow(color: Color, radius: Dp = 12.dp): Modifier {
    // approximating css box-shadow: 0 0 12px #22c55e
    // simplistic using drawBehind for colored shadow
    return this.drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = color.toArgb()
            frameworkPaint.setShadowLayer(
                radius.toPx(),
                0f,
                0f,
                color.toArgb()
            )
            it.drawRoundRect( // Need to know shape, defaulting
                left = 0f, top = 0f, right = size.width, bottom = size.height,
                radiusX = size.height/2, radiusY = size.height/2, // assuming capsule/circle for progress
                paint = paint
            )
        }
    }
}

fun Modifier.blurEffect(): Modifier {
    // Since we can't depend on RenderEffect (API 31+), we use a visual approximation 
    // or just assume generic modifier if strictly following Android 12+.
    // Here we just use a graphics layer alpha for basic styling as fallback
    return this.background(Color.Transparent) 
    // In real styling we would use RenderEffect.createBlurEffect
}

fun Modifier.shadowCustom(
    color: Color = Color.Black,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp
): Modifier {
    return this.drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = color.toArgb()
            frameworkPaint.setShadowLayer(
                blurRadius.toPx(),
                0f,
                offsetY.toPx(),
                color.toArgb()
            )
            it.drawRoundRect(
                left = 0f, top = 0f, right = size.width, bottom = size.height,
                radiusX = 16.dp.toPx(), radiusY = 16.dp.toPx(),
                paint = paint
            )
        }
    }
}

@Composable
fun Modifier.clickableWithScaling(onClick: () -> Unit): Modifier {
    return this.buttonClickEffect(onClick)
}

// Simple bounce click effect modifier
fun Modifier.buttonClickEffect(onClick: () -> Unit): Modifier {
    // Simplified: in a real implementation we would use interaction source and animate scale
    // For this demo static is fine, or use a basic clickable
    return this.clickable { onClick() }
}

@Preview
@Composable
fun CampaignDetailScreenPreview() {
    CampaignDetailScreen()
}
