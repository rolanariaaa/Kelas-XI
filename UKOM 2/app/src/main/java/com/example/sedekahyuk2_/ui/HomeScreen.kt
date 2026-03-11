package com.example.sedekahyuk2_.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// Colors
val PrimaryColor = Color(0xFF21C45D)
val BackgroundLight = Color(0xFFF6F8F7)
val TextColorDark = Color(0xFF1E293B) // slate-800
val TextColorGray = Color(0xFF94A3B8) // slate-400
val HeaderGradientStart = Color(0xFF22C55E)
val HeaderGradientEnd = Color(0xFF16A34A)

@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomNavigationBar(onNavigate) },
        containerColor = BackgroundLight
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()) // Allow content to scroll behind but padded
                .verticalScroll(scrollState)
        ) {
            // Header
            HeaderSection()

            // Floating Balance Card
            BalanceCardSection()

            // Quick Menu
            QuickMenuSection(onNavigate)

            // Campaigns
            LatestCampaignsSection()

            // Articles
            LatestArticlesSection()
            
            // Extra spacer for bottom nav visual
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Approximate height including padding
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(HeaderGradientStart, HeaderGradientEnd)
                )
            )
            .padding(top = 48.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Assalamualaikum 👋",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Ahmad Rizki",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Box {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBoQGxkU91EVODw81EZ0DINx8BZr1uE2G3WIHLoMaltJxhOv7GjT94MqXRkpSdaCEBD8w6sumxwn7Au9t5eLXg2ociBoOAk9BrvGBvXiYySGP_7x7cHR-Fy50lWWyAmGf311EbfHBMfkxU0XjwlHAQoAsxnnWYkDlnE8Ws_SzzJexbNUt_ZeLeMsLEOOM7IfLekJ9ftEsdKV_3ID-ylLjba7bDFeA-BOwBNdOBkkZ7aqH55FNucmLOwcrBFhL_TqZN-vBHHqSFHyg",
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White.copy(alpha = 0.3f), CircleShape),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(12.dp)
                        .background(PrimaryColor, CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
            }
        }
    }
}

@Composable
fun BalanceCardSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .offset(y = (-32).dp) // Negative margin to overlap
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp), spotColor = Color.Black.copy(alpha = 0.1f)),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFECFDF5)) // emerald-50
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(PrimaryColor.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Using a wallet-like icon
                        Icon(
                            imageVector = Icons.Rounded.AccountBalanceWallet,
                            contentDescription = "Wallet",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Saldo Infak",
                            color = TextColorGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Rp 500.000",
                            color = TextColorDark,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AddCircle,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Top Up", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun QuickMenuSection(onNavigate: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 0.dp), // Adjusting for the offset of previous element
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickMenuItem(
            icon = Icons.Rounded.Favorite,
            label = "Sedekah",
            color = PrimaryColor, // emerald
            backgroundColor = Color(0xFFD1FAE5), // emerald-100
            onClick = { onNavigate(Screen.DonationPopup) }
        )
        QuickMenuItem(
            // Use Payment or similar for Zakat
            icon = Icons.Rounded.Payments, 
            label = "Zakat",
            color = Color(0xFF2563EB), // blue-600
            backgroundColor = Color(0xFFDBEAFE), // blue-100
             onClick = { onNavigate(Screen.DonationPopup) }
        )
        QuickMenuItem(
            // VolunteerActivism replacement
            icon = Icons.Rounded.VolunteerActivism,
            label = "Donasi",
            color = Color(0xFFEA580C), // orange-600
            backgroundColor = Color(0xFFFFEDD5), // orange-100
             onClick = { onNavigate(Screen.DonationPopup) }
        )
        QuickMenuItem(
            icon = Icons.Rounded.History,
            label = "History",
            color = Color(0xFF9333EA), // purple-600
            backgroundColor = Color(0xFFF3E8FF), // purple-100
             onClick = { onNavigate(Screen.TransactionHistory) }
        )
    }
}

@Composable
fun QuickMenuItem(
    icon: ImageVector,
    label: String,
    color: Color,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(backgroundColor, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF475569) // slate-600
        )
    }
}

@Composable
fun LatestCampaignsSection() {
    Column(modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Latest Campaigns",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextColorDark
            )
            Text(
                text = "View All",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                modifier = Modifier.clickable { }
            )
        }

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            CampaignCard(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBHnQfExD6v_sF88dwooXYRPAK-3l30MUTWDZzlSQhZvOazpDz_zqgIFMiDDmyAQFO_qBOVX2Rn7I-oMnEhXvrN__Dd86dDZFK71fSMyAXFZTW3iUUNhANY8U-KLxXC_v5n9wi577hxSW7ThVrlhCssrk8-ALCDC_xfCQHRDffZSu889ToIxeov1NmhIE6I7h5-_lx-mmC-Oh6ZOQhJuGlqTdoiLmSZ9v6Ja6Sfzxuez62T72BWvc6qPF72B5V7-di1vu45d6oxkQ",
                title = "Pendidikan untuk Yatim",
                collected = "Rp 45.000.000",
                progress = 0.75f,
                target = "Rp 60jt"
            )
            Spacer(modifier = Modifier.width(16.dp))
            CampaignCard(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCFHupW7L4bvRbbPCLqFEKps-eQNGA_Zi_CHrp9-NgXaxYIkDLCZ7JXCikAEQn6hwct-jBBadsYyu3zJk9QoJKylS-a3lMytn2ViVa6eu4P0joL-H-joe0-noKsPL3K2_Dilq9Os_pxmjGY2HaWePN5J83OKtugqa2GvlfdaG3YOVc-xtdPsVZZKR7r7Zr9F-YbSKWdw5k2TFgAGkSDb4N7HlLmrXDJiC4Sp7eiTj3nmYAhoC8RIWwCi0YQn4FV2Y1-LfwQlyypew",
                title = "Renovasi Masjid Al-Ikhlas",
                collected = "Rp 120.000.000",
                progress = 0.40f,
                target = "Rp 300jt"
            )
        }
    }
}

@Composable
fun CampaignCard(
    imageUrl: String,
    title: String,
    collected: String,
    progress: Float,
    target: String
) {
    Card(
        modifier = Modifier
            .width(260.dp)
            .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp)), // slate-100
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColorDark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "Terkumpul", fontSize = 10.sp, color = TextColorGray, fontWeight = FontWeight.Medium)
                    Text(text = collected, fontSize = 12.sp, color = PrimaryColor, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                // Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(Color(0xFFF1F5F9), CircleShape) // slate-100
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(8.dp)
                            .background(PrimaryColor, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${(progress * 100).toInt()}% Selesai", fontSize = 10.sp, color = TextColorGray, fontWeight = FontWeight.Medium)
                    Text(text = "Target $target", fontSize = 10.sp, color = TextColorGray, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun LatestArticlesSection() {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 0.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Articles for You",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextColorDark
            )
            Text(
                text = "Read More",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                modifier = Modifier.clickable { }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ArticleItem(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuACTGa3YVS8N399QZ6gpAqjW4V4RJpgNLqx2ZeAf6CI2pz2gE11Vx-ZlGVeY_d_kFVeYYBEbCk5aNwaOQeNdRlu4aAZ-BCalvbaBM9LdtJSJnv91k1AnuShB8diHz5AxykOwGAvQinkYztMne1zFNp9XRqJeTBVCZ-iqgl-dzqvSXv-Er5lw8X7rdWnJV-ldm4IYy8M51DxihpvDboRxmoatmm9CDVBIePA71xOc3nu-9fShI-ILQh1Jq1FlwY361OC7Np3UjioCA",
                category = "Edukasi",
                title = "Keutamaan Sedekah di Waktu Subuh",
                readTime = "5 min read"
            )
            ArticleItem(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCKlDFE6vAQMGpE37ej1vyXqouTuM6vE3Vc813mIix_9VqwKa02U72M8PEaipRQNQHDi7mSx6z-_6eYUI6_c9ZqL0E4AISxgt5ERbwQ7AwJE1iA-ODWyMpBGrQMWXV3fh7vLku3WoYCtVIkJFGpytyglWuVCvbWxyQi3z-UjfVRfQ9nI6VhgeilfKhKDOYGTZWmR5oM3ugfyvoyconFYSbGFe5iRYbGeinmPyfHTivVDw5zLFBPp6LSMKWmBWozWWjGOoYKwuFIRA",
                category = "Zakat",
                title = "Panduan Lengkap Menghitung Zakat Maal",
                readTime = "8 min read"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ArticleItem(
    imageUrl: String,
    category: String,
    title: String,
    readTime: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp)) // slate-50
            .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = category,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextColorDark,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Schedule, // Fallback for schedule
                    contentDescription = null,
                    tint = TextColorGray,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = readTime,
                    fontSize = 10.sp,
                    color = TextColorGray
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(onNavigate: (Screen) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.9f))
            .border(1.dp, Color(0xFFF1F5F9)) // slate-100
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavIcon(icon = Icons.Rounded.Home, label = "Home", isSelected = true, onClick = { onNavigate(Screen.Home) })
            NavIcon(icon = Icons.Rounded.Explore, label = "Explore", isSelected = false, onClick = { })
            
            // Raised FAB
            Box(
                modifier = Modifier
                    .offset(y = (-24).dp)
                    .size(56.dp)
                    .background(PrimaryColor, CircleShape)
                    .border(4.dp, Color.White, CircleShape)
                    .shadow(elevation = 10.dp, shape = CircleShape, spotColor = PrimaryColor.copy(alpha = 0.4f))
                    .clickable { onNavigate(Screen.DonationPopup) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            
            // Reusing Home for "News" visual, normally would use Newspaper icon if available
            // Using a text label 'News' and a suitable replacement
            NavIcon(icon = Icons.Rounded.Article, label = "News", isSelected = false, onClick = { onNavigate(Screen.TransactionHistory) }) // Map News/History to History or keep News? Let's map News to History for flow or add History icon
            NavIcon(icon = Icons.Rounded.PersonOutline, label = "Profile", isSelected = false, onClick = { onNavigate(Screen.Profile) })
        }
    }
}

@Composable
fun NavIcon(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) PrimaryColor else Color(0xFF94A3B8), // slate-400
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) PrimaryColor else Color(0xFF94A3B8)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onNavigate = {})
}
