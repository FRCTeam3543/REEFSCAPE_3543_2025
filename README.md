# C4 Robotics – 2024–2025 Season: **Reefscape**

Welcome to the official **C4 Robotics** codebase for the 2024–2025 **Reefscape** FRC season!  
This repository contains all code, configuration, and documentation for our robot’s autonomous and teleoperated control systems.

---

## 🛠️ Overview

The 2024–2025 Reefscape robot features:

- **Swerve drive system** for maximum agility and field coverage
- **Modular arm and intake mechanisms** for efficient game piece handling
- **Autonomous routines** optimized for scoring in key zones
- **Sensor integration**: encoders, gyros, limit switches, and vision (AprilTags)

This code is written in **Java** using **WPILib**, following the **Command-based programming paradigm**.  
It’s designed to be modular, easy to test, and scalable for new features.

---

## 📂 Repository Structure
/C4-Reefscape-2024
│
├─ /src/main/java # Main robot code
│ ├─ /subsystems # Individual subsystems (drive, arm, intake, etc.)
│ ├─ /commands # Commands that control the robot behaviors
│ ├─ /autonomous # Pre-programmed autonomous routines
│ └─ Robot.java # Core Robot class
│
├─ /src/main/deploy # Deployable resources (trajectory files, configs)
├─ /docs # Documentation, diagrams, and notes
├─ /tests # Unit and integration tests
└─ build.gradle # Build configuration

## ⚡ Getting Started

### Requirements
- **Java 23**
- **WPILib 2024–2025**
- Compatible IDE (VS Code or IntelliJ IDEA recommended)
- FRC Driver Station for testing

### Setup
1. Clone the repository:
```
``` bash
git clone https://github.com/C4Robotics/Reefscape-2024.git
```
Open in your IDE and install WPILib dependencies.
onnect to the robot and deploy code via the Driver Station.

## 🎮 Robot Controls

### Driver Controls:
- Left Stick – Move robot (translation)
- Right Stick – Rotate robot (heading)
- Buttons – Intake, outtake, arm positions, etc.

### Operator Controls:
- Arm control
- Intake/outtake operations
- Mode switching for autonomous routines

Detailed control mappings are documented in /docs/Controls.md.

## 🤖 Autonomous Routines
- Non-existant

## 🧪 Testing
- Unit and integration tests are included under /tests.
Run using:
```
./gradlew test
```

### Tests include:
- Subsystem simulation
- Command verification
- Trajectory validation

## 📈 Notes / TODOs
- Improve autonomous path smoothing
- Add more vision-based targeting for cones/cubes
- Finalize PID tuning for swerve modules

## 💡 Contributions
- This repository is maintained by Team 3543 – C4 Robotics.
- Contributions are managed by lead programmers; for access, contact the programming lead.
