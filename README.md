this app is create in andriod studio using the help of gemini
#  Demo Photo Frame

A simple **digital picture frame** Android app built with **Kotlin** and **Jetpack Compose**.  
It cycles through a list of photos hosted on **Amazon S3**, showing each image for ten seconds with a smooth cross-fade animation.  
Users can **pause** or **resume** the slideshow at any time.

---

##  Features

- Loads photos from remote URLs using **Coil**  
- Automatically switches every 10 seconds  
- Smooth **Crossfade** transition  
- **Pause / Resume** button  
- Simple **bordered frame** UI built entirely with Compose  
- Works on any Android device running Android 5.0 (Lollipop) or higher  

---

## Tech Stack

| Library | Purpose |
|----------|----------|
| Kotlin | Main language |
| Jetpack Compose | Declarative UI |
| Material 3 | Buttons, icons, layouts |
| Coil (Compose) | Asynchronous image loading |
| Amazon S3 | Cloud image storage |

---

##  How It Works

1. A list of S3 image URLs is defined in `DigitalPictureFrame()`.  
2. Every 10 seconds `currentIndex` increments, showing the next photo.  
3. The **Crossfade** animation smoothly transitions between images.  
4. The **Pause** button toggles a Boolean `isPaused`; when true, the slideshow stops advancing.  

---

##  File Structure

