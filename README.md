# 🚆 Railway Ticket Booking Management System

A console-based application developed in **Java** using **Object-Oriented Programming (OOPs)** principles, integrated with **PostgreSQL** through **JDBC**, to simulate core functionalities of a railway ticket booking system.

## 📌 Features

* 🚉 View Available Trains
* 🧾 Book Train Tickets
* 📋 View Booking History
* ❌ Cancel Bookings
* 🔐 User Authentication (Registration & Login)
* 🗃️ PostgreSQL-based persistent data storage


## 🛠️ Tech Stack

* **Java** — Core application logic using Object-Oriented Programming
* **PostgreSQL** — Relational database to store train, user, and booking details
* **JDBC (Java Database Connectivity)** — Database interaction
* **Console-Based UI** — Text-based interface for simplicity and accessibility


## 🧱 Project Structure

```
RailwayBookingSystem/
├── db/                 # SQL scripts for table creation
├── model/              # Java classes for entities (User, Train, Booking)
├── dao/                # Data access layer (JDBC interaction)
├── service/            # Business logic
├── util/               # Utility classes (DB connection, input validation)
├── RailwayApp.java     # Main application entry point
└── README.md
```


## 🗄️ Database Tables

* **Users**: `id`, `name`, `email`, `password`
* **Trains**: `train_id`, `train_name`, `source`, `destination`, `available_seats`, `fare`
* **Bookings**: `booking_id`, `user_id`, `train_id`, `passenger_name`, `status`, `booking_date`

> SQL scripts to create and populate these tables are available in the `/db` folder.


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/RailwayBookingSystem.git
cd RailwayBookingSystem
```

### 2. Set Up PostgreSQL

* Create a new database:

  ```sql
  CREATE DATABASE railwaydb;
  ```
* Run the SQL scripts from the `db/` folder to create required tables.

### 3. Configure Database Credentials

Update your DB config in `DBUtil.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/railwaydb";
private static final String USER = "your_db_user";
private static final String PASSWORD = "your_db_password";
```

### 4. Compile and Run the Project

Compile:

```bash
javac RailwayApp.java
```

Run:

```bash
java RailwayApp
```


## 📚 OOP Concepts Used

* **Encapsulation** – All data models are properly encapsulated.
* **Inheritance** – Common functionality is abstracted in parent classes.
* **Abstraction** – DAO and Service layers hide complex logic from the main program.
* **Polymorphism** – Methods overloaded/overridden where applicable for flexibility.


## ✅ Example Use Cases

* A user can **register/login** into the system.
* After logging in, the user can:

  * View available trains
  * Book a ticket for a train
  * View all their previous bookings
  * Cancel any existing booking

## 🧪 Future Enhancements

* Add Admin functionalities (e.g., add/remove trains)
* GUI support using JavaFX or Swing
* Email confirmation on ticket booking
* Support for payment integration
