<<<<<<< HEAD
# 🌡️ Temperature Monitoring System

[![Java CI with Maven](https://github.com/ImanDahliaa/TemperatureMonitoringSystem/actions/workflows/maven.yml/badge.svg)](https://github.com/ImanDahliaa/TemperatureMonitoringSystem/actions/workflows/maven.yml)

A beginner-friendly Java Swing application that simulates temperature monitoring with alert messages and a modern dark theme.

## 🚀 Features
- Random temperature generation (20°C to 60°C)
- Email and SMS alerts for high temperature
- Two UIs:
  - `MainWindow`: Manual temperature check
  - `Room`: Auto-monitoring every 5 seconds
- FlatLaf dark theme for clean visuals

## 📸 Screenshots

### 🟢 Start Screen
![Start Screen](screenshots/start.png)

### ✅ Safe Temperature
![Safe Temperature](screenshots/safe-temp.png)

### 🔴 High Temperature
![High Temperature](screenshots/high-temp.png)

## 🧪 How to Run
1. Open in NetBeans, IntelliJ, or Eclipse.
2. Run either:
   - `projectUi.MainWindow`
   - `projectOperation.Room`

## 📥 Download ZIP
To download the project as a ZIP file:
1. Go to the repository page on GitHub.
2. Click the green **Code** button.
3. Select **Download ZIP**.
4. Extract the ZIP file and open it in your IDE.

## 📦 Maven Setup
This project can also be built with **Maven**.

1. Ensure you have Maven installed (`mvn -v` to check).
2. Place the provided `pom.xml` in the root of the project.
3. Build the project:
   ```bash
   mvn clean package

## 📁 Folder Structure

- src/
  - projectOperation/
    - Sensor.java
    - TemperatureSensor.java
    - AlertSystem.java
    - EmailAlert.java
    - SmsAlert.java
    - Room.java
  - projectUi/
    - MainWindow.java

## 🛠️ Technologies
- Java Swing
- FlatLaf (for dark theme)
- Git + GitHub

## 👨‍💻 Author

- **Name:** Iman Dahliaa
- **Location:** Perlis, Malaysia  
- **Education:** Bachelor of Electronics Engineering Technology
- **GitHub:** [Iman Dahliaa](https://github.com/ImanDahliaa)

## 📄 License
This project is for educational purposes.
=======
# Temperature Monitoring System

A Java Swing-based temperature monitoring system that simulates real-time temperature readings and triggers alerts using Email and SMS notification mechanisms.

## Features
- Random temperature simulation (20°C – 60°C)
- Email alert system
- SMS alert system
- Dark UI using FlatLaf
- Object-Oriented Programming (OOP) principles

## Technologies Used
- Java (Swing)
- FlatLaf 3.7
- NetBeans (Ant Project)

## Project Structure
- `projectOperation` – Core logic and alert system
- `projectUi` – GUI (Swing)

## How to Run
1. Open the project in NetBeans
2. Ensure FlatLaf JAR is added to Libraries
3. Run `MainWindow` or `Room` class

## Author
**Mohamad Iman**  
Bachelor of Electronic Engineering Technology (Hons.)  
Universiti Malaysia Perlis (UniMAP)
>>>>>>> 0dee82e (Create README.md)
