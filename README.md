# 🏨 Hotel Reservation System

A web-based Hotel Reservation System developed using Spring Boot, Spring Data JPA, Thymeleaf, and MySQL.
This project provides a simple and user-friendly interface to manage hotel rooms, customers, and reservations efficiently.

---

# 📌 Project Overview

The Hotel Reservation System is designed to automate and simplify hotel room booking operations.
It allows users or hotel staff to view available rooms, book rooms, manage customer details, and track reservations in real time.

The system ensures accurate room availability, reduces manual errors, and improves overall operational efficiency.

---

# 🎯 Project Objectives

Develop a GUI-based hotel reservation application

Enable room booking with availability checking

Manage customer and reservation records efficiently

Implement real-time room availability updates

Follow clean architecture using Spring Boot best practices

---


# ✨ Features

## 🏨 Room Management

View all rooms

Check room availability

Display room type and pricing

## 📅 Reservation Management

Book rooms

Track check-in and check-out dates

Reservation status handling (Confirmed, Checked-in, Cancelled)

## 👤 Customer Management

Store customer details (name, email, mobile)

Associate customers with reservations

## 📊 Dashboard

Centralized navigation for rooms, reservations, and customers

## 🎨 User-Friendly UI

Clean and responsive interface using HTML & CSS

Thymeleaf template integration

## 💾 Database Integration

Persistent data storage using MySQL

JPA-based ORM mapping

---

# 🛠️ Technology Stack


Layer	Technology

Backend:	Java, Spring Boot

ORM:	Spring Data JPA, Hibernate

Frontend:	Thymeleaf, HTML, CSS

Database:	MySQL

Build Tool:	Maven

IDE:	STS (Spring Tool Suit)

Version Control:	Git & GitHub


---

# 📂 Project Structure

Hotel-Reservation-System
│

├── src/main/java/com/hotel

│   ├── controller        # Handles web requests

│   ├── entity            # JPA entities (Room, Customer, Reservation)

│   ├── repository        # JPA repositories

│   ├── service           # Business logic

│   ├── config            # DataLoader and configuration

|   ├── exception 

│   └── HotelReservationSystemApplication.java

│

├── src/main/resources

│   ├── templates         # Thymeleaf HTML pages

│   ├── static/css        # CSS files

│   └── application.properties

│

├── pom.xml               # Maven dependencies

└── README.md

---


# 🧪 Sample Data

Rooms and customers are auto-loaded using CommandLineRunner

Includes a mix of available and booked rooms

Random reservation dates and statuses for testing

---

# Output Screenshots



# 📈 Future Enhancements

🔐 User authentication & role-based access

💳 Online payment gateway integration

📱 Fully responsive UI (Bootstrap)

📊 Admin analytics dashboard

🧾 Invoice and booking history generation

🌐 REST API support for mobile applications

---

# 📚 Learning Objectives

Through this project, I learned:

Spring Boot MVC architecture

Spring Data JPA and entity relationships

Thymeleaf template engine integration

Database design and ORM concepts

Error handling and UI navigation

Real-world application structure

Git and GitHub project management

---

# 👩‍💻 Author

Chaitali Shende

Java & Spring Boot Developer

🔗 LinkedIn: www.linkedin.com/in/chaitali-shende-a28779256





JPA-based ORM mapping
