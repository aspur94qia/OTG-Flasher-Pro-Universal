package com.otg.flasherpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selTab by remember { mutableStateOf(0) }
            var log by remember { mutableStateOf("OTG Flasher Pro Universal v2.0 Ready\n") }
            fun addLog(s: String) { log += "\n> $s" }

            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF0F1A1F),
                    surface = Color(0xFF0F1A1F),
                    primary = Color(0xFF4CC9A0)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F1A1F))) {
                    Column(modifier = Modifier.fillMaxSize().padding(16.dp).padding(bottom = 70.dp)) {
                        // HEADER
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("OTG Flasher Pro", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                                Text("v2.0 UNIVERSAL • OTG Flash semua HP", color = Color(0xFF4CC9A0), fontSize = 10.sp)
                            }
                            Icon(Icons.Default.Usb, null, tint = Color(0xFF4CC9A0))
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2E30)), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("LOG", color = Color.Gray, fontSize = 10.sp)
                                Text(log, color = Color.White, fontSize = 11.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Stat(label = "Status", value = "Ready", modifier = Modifier.weight(1f))
                            Stat(label = "Mode", value = "Universal", modifier = Modifier.weight(1f))
                            Stat(label = "OTG", value = "OK", modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(onClick = { addLog("Scanning device...") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CC9A0))) {
                            Text("START FLASH", color = Color.Black, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Step(1, "Connect OTG", "Colok kabel OTG", done = true, active = false)
                        Step(2, "Detect Device", "Deteksi HP target", done = false, active = true)
                        Step(3, "Flash", "Proses flashing universal", done = false, active = false)
                    }

                    // Bottom Nav
                    Row(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(Color(0xFF1A2A2F)).padding(8.dp), horizontalArrangement = Arrangement.SpaceAround) {
                        Nav("Home", Icons.Default.Home, selTab == 0) { selTab = 0 }
                        Nav("Flash", Icons.Default.Bolt, selTab == 1) { selTab = 1 }
                        Nav("Log", Icons.Default.List, selTab == 2) { selTab = 2 }
                        Nav("Set", Icons.Default.Settings, selTab == 3) { selTab = 3 }
                    }
                }
            }
        }
    }
}

@Composable
fun Step(no: Int, title: String, sub: String, done: Boolean = false, active: Boolean = false) {
    Row {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(26.dp).clip(CircleShape).background(if (done) Color(0xFF1E3A33) else if (active) Color(0xFF3A2E1E) else Color(0xFF2A2E30)), contentAlignment = Alignment.Center) {
                Text("$no", color = if (active) Color(0xFFEAB308) else if (done) Color(0xFF4CC9A0) else Color.Gray, fontSize = 11.sp)
            }
            if (no < 7) Box(modifier = Modifier.width(2.dp).height(20.dp).background(Color(0xFF2A2E30)))
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            Text(title, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(sub, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

@Composable
fun Stat(label: String, value: String, modifier: Modifier = Modifier) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2E30)), shape = RoundedCornerShape(8.dp), modifier = modifier) {
        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(label, color = Color.Gray, fontSize = 9.sp)
            Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Composable
fun Nav(label: String, icon: ImageVector, sel: Boolean, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = if (sel) Color(0xFF4CC9A0) else Color.Gray, modifier = Modifier.size(22.dp))
        Text(label, color = if (sel) Color(0xFF4CC9A0) else Color.Gray, fontSize = 9.sp)
    }
}
