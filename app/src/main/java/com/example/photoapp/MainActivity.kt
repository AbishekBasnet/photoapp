package com.example.photoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PhotoFrameApp()
            }
        }
    }
}

@Composable
fun PhotoFrameApp() {
    val imageUrls = listOf(
        // Ferrari
        "https://photobucket21.s3.ca-central-1.amazonaws.com/ferrari.webp",
        // Lamborghini
        "https://photobucket21.s3.ca-central-1.amazonaws.com/lambo.webp",
        // Bugatti
        "https://photobucket21.s3.ca-central-1.amazonaws.com/bugatti.webp",
        // Porsche (the one you provided)
        "https://photobucket21.s3.ca-central-1.amazonaws.com/porche.webp?response-content-disposition=inline&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEBIaDGNhLWNlbnRyYWwtMSJIMEYCIQD4ijc%2FEqhGo5XJpOHD%2B4tyPVv2z5POy2TmbnoiRE8NrQIhANb%2BCeHdpBdSMbmuJiAqKkMP7hBKYJmjYVXjWnfitOE6KsIDCMv..."
    )

    var currentIndex by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }

    // Automatic rotation every 10 seconds
    LaunchedEffect(isPaused) {
        while (true) {
            if (!isPaused) {
                delay(10_000)
                currentIndex = (currentIndex + 1) % imageUrls.size
            } else {
                delay(500)
            }
        }
    }

    // Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Custom digital frame border
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        width = 10.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFFD700), Color(0xFFE5C100), Color(0xFFFFA500))
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Crossfade(targetState = imageUrls[currentIndex]) { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "Slideshow image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pause/Resume Button
            Button(
                onClick = { isPaused = !isPaused },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPaused) Color(0xFF2E7D32) else Color(0xFFD32F2F)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = if (isPaused) "Resume Slideshow" else "Pause Slideshow",
                    color = Color.White
                )
            }
        }
    }
}
