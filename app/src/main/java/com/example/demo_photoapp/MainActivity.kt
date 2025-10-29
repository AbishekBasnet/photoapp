package com.example.demo_photoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.demo_photoapp.ui.theme.DemoPhotoAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoPhotoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DigitalPictureFrame(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DigitalPictureFrame(modifier: Modifier = Modifier) {
    val images = listOf(
        "https://demo-bucket1335.s3.ca-central-1.amazonaws.com/bugatti.webp?response-content-disposition=inline&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEB8aDGNhLWNlbnRyYWwtMSJGMEQCIF6b8ylGu2bYfVp1EWCMnpgDnCicWwAXndVmGD4qs45uAiA4zZwDbGsQ9qCO0QQC41iLZn7PwxeH4URfDMmBvywi9yrCAwjY%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDkyNzMwNDMyNjUyMCIMTTnzK4dVeOAiJHZJKpYD0y2o2zaEPUD%2BNhFZjPYJWq4Z1KKsqxGKqqyXTeonjPx6PSgwIjYpUNcPvl7iCKqmvQ%2BT0DlKTaS%2B5IptuJv4qIP7lyrtbjQdM5P5OPllo%2FBU5BSCHad47Xby%2Fmu%2BsDdmlLki303FNnXVhzEc01bYlGzLYF2178otRLAqI3iNu3c34l70NAk2vpN8ldp6ryWWoI7z%2F7WuISfA60MiHkucrifcHyVSDDZsuDhsOZBBwWpGqkKDpHmcNX32tRE4imaqHZXMiQ8RjlE42k7quNVYo2hnZOnr%2FMMXg%2BskkcHsS7VsxbSXPuHOkwB5fX8jEYuzWgo%2BQKUXS4nISq7vn0UQlyyM9ckWl%2BTSYDKGoFICnGQT4562rlM6manPAEclzbYoNafoP1HdYKlVDxs1DgssZ1Fjr0kE7ot2X1c2UTEFgKr3YZuU68vkIX%2FiGahDIRqO89fzsSyscY6Zx%2BC2ru5NxauCQzZKGM2elfNvwGp%2BZVWZlT3NnUq7DnI6H4l9ie9uVeLoNFgxfsO8jvzh1sAX2clfuZasyTClyojIBjrfAtIoZi5SKDCbQcOvIMEgCsoGW6%2Falv%2BaYYCO8Tws6El2kH1hBC11N2IPKn%2B0bO%2FSdBoJDYwO6HNhcK4wCfhfJfK%2BqYiQpPJ08%2BWiXcxJDA0I2pvBBRyZUAx%2Fm7ha8NvIaQAOHH8PBDt5tYv0VngP%2Bx4eWtZNl6CW3Mf8zHX%2BwUuzCh588xXX0m8l7zQPYxczT5Li59fNt0hZKwvDRp0vaG924cV%2BysRTgQmk%2FrTbUesiLJi0o7y2I0bt2e3J2F2GFSpaa6bXvBFowmaKRiQpJk9ioFAvzhrL56uK%2F9jf%2B4ByxVrTlV6RzRqJ8%2B%2FJauQDfbppFguljpmmeYTpbjdjLl%2FUCnw9t%2BsLiuMvlJEQuKGVl%2FXyu3HX4V6cmzJURfvrBs0gJ6mPD%2BKnIxoxEs2ZD3wUn6fz5usIEzfd8nUBgeDjGjwfVCTCoCIGcC9P0Y3eW2VzCgEssEAchv6vPAyz3Q%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIA5PZ5FDV4MFH5SUH6%2F20251029%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20251029T150818Z&X-Amz-Expires=43200&X-Amz-SignedHeaders=host&X-Amz-Signature=4550d73904e8da9f113fc160499cc3e191ab997c03acc0a3e8abaa6f39d3fc75",
        "https://demo-bucket1335.s3.ca-central-1.amazonaws.com/ferrari.webp?response-content-disposition=inline&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEB8aDGNhLWNlbnRyYWwtMSJHMEUCIGy4qxXJpSSZcCTULeSBUDUjbiq0ukxfTdTIzcKnnqGFAiEA%2FahYRXay%2Blll0b3Ko4LTKf8f3lfK%2BaufBUohWYGFLz8qwgMI2P%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw5MjczMDQzMjY1MjAiDDAD7%2F53sXExnUwnVCqWAxzv9XhadHd%2F%2BzGx2K%2Bsp1RfAipGBMV2wgAkE8N6MurYkTf89DC7rzCco%2F2aUL%2BfvjOqECnM%2BmvZtuNcb4Pcy%2FSwR3w%2BWxfC7FJO7t5J6RsllJD%2FqbpyXPRoBcjP4VD4umZlbyVbrmN%2BJvRfzRJ2vRuf6pEPHELweF2lWVhSb4r5gMWbZqeoJC7KKyi3REpRjjpKiKh5yQRoUO2GKkxuVFCHjwcBp2u%2BHzfW%2FsppSxdyxEg1XTuLoKGpKdWOlxhnHqOEHDQiQ4RjvC7AWDzV5OwL9WjUmy5E%2FBA3DST%2F1xLrieCMEl4V2WMoBITxxNAgoGd9%2BHDyDT%2FJ7CWGMa3V1qYHhSE0a12uPxK1u3ORV77XRwPi2yYN4ZcaUEECHI6ZGYu0Vt37zdotT%2FyY32kmwSpNrsk8QuvPZRBY9OUmkOi6Ho6VOE%2BKsGW%2B%2B0Mx1uy8lQ%2BcG0KNLOaGH3XTbQWUmWeOtcT7PcvE3q%2Fdwks24RRTW5fbtmfEve27Hgzp55MKlLWh%2FXdFP%2B%2BNArubaFu3sPpyD52wDggwpcqIyAY63gJdko0sqBGBUp1vyQOtysZL%2BWIEFCeCn3iR1Qj2MET37XGxiimuslryZ7wDKJgoDITsjNSG8ycRszHgAyvYBFz%2FgTW22sm%2B%2FHrYkfoZKCah59wX%2BNCwG9S%2FNmjRgliBxy24%2FBWVa0YwkzoRyesradgjWIlxOueqO2K4HcnPsD%2Faik96sZGpgkOXDSDncFtw%2BtA6tJudsomlbaAfPxdRXM0L3WC%2BN0el4iTQhk4VWsPVWACfxQKbfHocZEKURA7KnPU7tscbFa%2FQ8xc4HG%2BJZMstvwjm58cuA2Sr1O9r%2B6W7ITWzTlQPzBtnUeUi0WYXgSQmpQy4RRmPqy3scakUvdV%2Bsvq4TKyuEVJMIusUzq9B7MbVkyGogFEUgucwl3wFNDBpjq4t1iRnH9LAsoRSAoZB2zDus98lAaZUtZMeWDk8%2BCFpW6%2Fz8f9LxczYI%2B4cg1Zr%2FDXl0F6Zrno7Ld3aSA%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIA5PZ5FDV4F467K4KJ%2F20251029%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20251029T144943Z&X-Amz-Expires=43200&X-Amz-SignedHeaders=host&X-Amz-Signature=f5eeb1c6294d24660580b46bb3403020dc706defcc518e01c800af4a03e3dd28",
        "https://demo-bucket1335.s3.ca-central-1.amazonaws.com/lambo.webp?response-content-disposition=inline&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEB8aDGNhLWNlbnRyYWwtMSJHMEUCIGy4qxXJpSSZcCTULeSBUDUjbiq0ukxfTdTIzcKnnqGFAiEA%2FahYRXay%2Blll0b3Ko4LTKf8f3lfK%2BaufBUohWYGFLz8qwgMI2P%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw5MjczMDQzMjY1MjAiDDAD7%2F53sXExnUwnVCqWAxzv9XhadHd%2F%2BzGx2K%2Bsp1RfAipGBMV2wgAkE8N6MurYkTf89DC7rzCco%2F2aUL%2BfvjOqECnM%2BmvZtuNcb4Pcy%2FSwR3w%2BWxfC7FJO7t5J6RsllJD%2FqbpyXPRoBcjP4VD4umZlbyVbrmN%2BJvRfzRJ2vRuf6pEPHELweF2lWVhSb4r5gMWbZqeoJC7KKyi3REpRjjpKiKh5yQRoUO2GKkxuVFCHjwcBp2u%2BHzfW%2FsppSxdyxEg1XTuLoKGpKdWOlxhnHqOEHDQiQ4RjvC7AWDzV5OwL9WjUmy5E%2FBA3DST%2F1xLrieCMEl4V2WMoBITxxNAgoGd9%2BHDyDT%2FJ7CWGMa3V1qYHhSE0a12uPxK1u3ORV77XRwPi2yYN4ZcaUEECHI6ZGYu0Vt37zdotT%2FyY32kmwSpNrsk8QuvPZRBY9OUmkOi6Ho6VOE%2BKsGW%2B%2B0Mx1uy8lQ%2BcG0KNLOaGH3XTbQWUmWeOtcT7PcvE3q%2Fdwks24RRTW5fbtmfEve27Hgzp55MKlLWh%2FXdFP%2B%2BNArubaFu3sPpyD52wDggwpcqIyAY63gJdko0sqBGBUp1vyQOtysZL%2BWIEFCeCn3iR1Qj2MET37XGxiimuslryZ7wDKJgoDITsjNSG8ycRszHgAyvYBFz%2FgTW22sm%2B%2FHrYkfoZKCah59wX%2BNCwG9S%2FNmjRgliBxy24%2FBWVa0YwkzoRyesradgjWIlxOueqO2K4HcnPsD%2Faik96sZGpgkOXDSDncFtw%2BtA6tJudsomlbaAfPxdRXM0L3WC%2BN0el4iTQhk4VWsPVWACfxQKbfHocZEKURA7KnPU7tscbFa%2FQ8xc4HG%2BJZMstvwjm58cuA2Sr1O9r%2B6W7ITWzTlQPzBtnUeUi0WYXgSQmpQy4RRmPqy3scakUvdV%2Bsvq4TKyuEVJMIusUzq9B7MbVkyGogFEUgucwl3wFNDBpjq4t1iRnH9LAsoRSAoZB2zDus98lAaZUtZMeWDk8%2BCFpW6%2Fz8f9LxczYI%2B4cg1Zr%2FDXl0F6Zrno7Ld3aSA%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIA5PZ5FDV4F467K4KJ%2F20251029%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20251029T145119Z&X-Amz-Expires=43200&X-Amz-SignedHeaders=host&X-Amz-Signature=33b4fe5dffcc496ddb84846a8933ab2e753ca234eb5157a2510165961ace1c56",
        "https://demo-bucket1335.s3.ca-central-1.amazonaws.com/porche.webp?response-content-disposition=inline&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEB8aDGNhLWNlbnRyYWwtMSJGMEQCIF6b8ylGu2bYfVp1EWCMnpgDnCicWwAXndVmGD4qs45uAiA4zZwDbGsQ9qCO0QQC41iLZn7PwxeH4URfDMmBvywi9yrCAwjY%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDkyNzMwNDMyNjUyMCIMTTnzK4dVeOAiJHZJKpYD0y2o2zaEPUD%2BNhFZjPYJWq4Z1KKsqxGKqqyXTeonjPx6PSgwIjYpUNcPvl7iCKqmvQ%2BT0DlKTaS%2B5IptuJv4qIP7lyrtbjQdM5P5OPllo%2FBU5BSCHad47Xby%2Fmu%2BsDdmlLki303FNnXVhzEc01bYlGzLYF2178otRLAqI3iNu3c34l70NAk2vpN8ldp6ryWWoI7z%2F7WuISfA60MiHkucrifcHyVSDDZsuDhsOZBBwWpGqkKDpHmcNX32tRE4imaqHZXMiQ8RjlE42k7quNVYo2hnZOnr%2FMMXg%2BskkcHsS7VsxbSXPuHOkwB5fX8jEYuzWgo%2BQKUXS4nISq7vn0UQlyyM9ckWl%2BTSYDKGoFICnGQT4562rlM6manPAEclzbYoNafoP1HdYKlVDxs1DgssZ1Fjr0kE7ot2X1c2UTEFgKr3YZuU68vkIX%2FiGahDIRqO89fzsSyscY6Zx%2BC2ru5NxauCQzZKGM2elfNvwGp%2BZVWZlT3NnUq7DnI6H4l9ie9uVeLoNFgxfsO8jvzh1sAX2clfuZasyTClyojIBjrfAtIoZi5SKDCbQcOvIMEgCsoGW6%2Falv%2BaYYCO8Tws6El2kH1hBC11N2IPKn%2B0bO%2FSdBoJDYwO6HNhcK4wCfhfJfK%2BqYiQpPJ08%2BWiXcxJDA0I2pvBBRyZUAx%2Fm7ha8NvIaQAOHH8PBDt5tYv0VngP%2Bx4eWtZNl6CW3Mf8zHX%2BwUuzCh588xXX0m8l7zQPYxczT5Li59fNt0hZKwvDRp0vaG924cV%2BysRTgQmk%2FrTbUesiLJi0o7y2I0bt2e3J2F2GFSpaa6bXvBFowmaKRiQpJk9ioFAvzhrL56uK%2F9jf%2B4ByxVrTlV6RzRqJ8%2B%2FJauQDfbppFguljpmmeYTpbjdjLl%2FUCnw9t%2BsLiuMvlJEQuKGVl%2FXyu3HX4V6cmzJURfvrBs0gJ6mPD%2BKnIxoxEs2ZD3wUn6fz5usIEzfd8nUBgeDjGjwfVCTCoCIGcC9P0Y3eW2VzCgEssEAchv6vPAyz3Q%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIA5PZ5FDV4MFH5SUH6%2F20251029%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20251029T150854Z&X-Amz-Expires=43200&X-Amz-SignedHeaders=host&X-Amz-Signature=545eb52c6d387de827af4b18b5dd3e46cfad46d252d907fa68e1c09ccb665478"
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = currentIndex, key2 = isPaused) {
        if (!isPaused) {
            delay(10000)
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    BorderStroke(
                        10.dp,
                        Color.DarkGray
                    )
                )
                .padding(16.dp)
        ) {
            Crossfade(targetState = currentIndex, animationSpec = tween(500)) { index ->
                Image(
                    painter = rememberAsyncImagePainter(images[index]),
                    contentDescription = "photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Pineapple",
                    tint = Color.White
                )
                Text(text = "This is a pineapple frame", color = Color.White)
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { isPaused = !isPaused }) {
                Text(text = if (isPaused) "Resume" else "Pause")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoPhotoAppTheme {
        DigitalPictureFrame()
    }
}
