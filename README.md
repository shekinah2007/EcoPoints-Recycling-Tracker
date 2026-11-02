# 🌱 EcoPoints – Recycling Management System

A simple Java console-based application that encourages household recycling by tracking recyclable materials and rewarding users with EcoPoints based on the weight of materials recycled.

## 📌 Project Overview

EcoPoints is a household recycling management system developed using Core Java. It allows users to register households, record recycling activities, calculate eco points, and generate community recycling reports.

The application uses Java File I/O to store household information and recycling records, ensuring that data is retained even after the program is closed.

## ✨ Features

- **Household Registration:** Register households with a unique ID, name, address, and joining date.
- **Recycling Event Tracking:** Record recycling activities for plastic, glass, metal, and paper.
- **EcoPoints Calculation:** Earn 10 EcoPoints for every kilogram of recycled material.
- **Household Management:** View registered households and their recycling history.
- **Recycling Statistics:** Calculate total recycled weight and total points for each household.
- **Community Reports:** Identify the household with the highest points and calculate total community recycling weight.
- **Data Persistence:** Store and retrieve household profiles and recycling records using Java File I/O.
- **Exception Handling:** Handle invalid inputs, negative weights, duplicate IDs, and file-related errors.

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework (HashMap and ArrayList)
- Java Date and Time API
- Java File I/O
- Exception Handling

## 📂 Project Structure

```text
EcoPoints/
│
├── src/
│   ├── Main.java
│   ├── Household.java
│   ├── RecyclingEvent.java
│   └── EcoPointsManager.java
│
├── households.txt
├── recycling_events.txt
└── README.md
```

*The text files are generated automatically when data is saved.*

## ⚙️ EcoPoints Calculation

The application awards 10 points for every kilogram of recycled material.

**Formula:**

```text
EcoPoints = Weight (kg) × 10
```

Example:

| Material | Weight | Points Earned |
|---|---:|---:|
| Plastic | 2 kg | 20 |
| Glass | 3 kg | 30 |
| Paper | 5 kg | 50 |

## 🚀 How to Run

1. Clone the repository:

```bash
git clone <your-repository-url>
```

2. Open the project in IntelliJ IDEA or your preferred Java IDE.

3. Ensure that Java is installed and configured.

4. Run `Main.java`.

5. Use the console menu to register households, log recycling events, and view reports.

## 📚 Concepts Practiced

This project helped me practise and apply:

- Classes, objects, constructors, and encapsulation.
- HashMap and ArrayList for data management.
- Java LocalDate for date handling.
- Reading and writing data using Java File I/O.
- Exception handling and input validation.
- Separating application logic into multiple classes.

## 🎯 Project Objective

The objective of this project is to apply Core Java concepts to a practical problem while promoting awareness of household recycling through a simple reward-based system.

---

**Developed as a Core Java learning project.**
