# 🩸 Blood Donation Management System

Welcome to the **Blood Donation Management System**!  
This repository contains a **two-phase project** built in Java to manage donors, donations, and blood requests.

---

## 📌 About This Repository

This project is developed as part of a **university software engineering exam** and showcases a progressive development from **DAO/JDBC** to a **full RMI + Hibernate client-server system**.

### Branches Overview

| Branch      | Description |
|------------|-------------|
| `main`     | **Phase 2 (Final)** — Hibernate ORM + RMI-based client-server version. Default branch. Exam-ready and production-like. |
| `phase-1`  | **Phase 1 (Initial)** — DAO + JDBC + Swing GUI version. Used for learning and initial prototype. |

> **Note:** If you are new to this project, start with the `main` branch to explore the full functionality.
![Alt text](path/to/image)
---

## 🏗 Phase 2 — Final Version (Default Branch)

**Technologies Used:**  
- Java (Swing for GUI)  
- Hibernate ORM  
- Java RMI for client-server communication  
- JavaMail (optional OTP/email feature)  
- MySQL / MariaDB  
- GitHub for version control  

**Structure:**  
BloodDonationManagementSystem/
├── client/ → Client-side GUI

├── server/ → Server-side RMI services

├── model/ → Shared entities

├── service/ → Server-side logic

├── rmi/ → RMI interfaces

├── util/ → Utilities (HibernateUtil, EmailUtil)

├── resources/ → Config files

├── view/ → Client GUI forms

├── README.md → This file



**How to Run:**  

1. **Server**
   - Open the `server` folder in NetBeans
   - Ensure the database is running and `hibernate.cfg.xml` has correct credentials
   - Run `ServerMain.java` to start the RMI server

2. **Client**
   - Open the `client` folder in NetBeans
   - Ensure the server is running
   - Run `ClientMain.java` to launch the GUI

---

## 🏗 Phase 1 — Initial Version (`phase-1` branch)

- DAO + JDBC version  
- Swing GUI for donor, donation, and blood request management  
- Useful for learning and understanding the system’s evolution  

> To explore Phase 1, switch to the `phase-1` branch in GitHub.

---

## 🗂 Database Schema

### Donors
- `donor_id` INT (PK)  
- `name` VARCHAR(100)  
- `blood_type` VARCHAR(10)  
- `email` VARCHAR(100)  
- `phone` VARCHAR(20)  

### Donations
- `donation_id` INT (PK)  
- `donor_id` INT (FK)  
- `donation_date` DATETIME  
- `volume_ml` INT  
- `remarks` VARCHAR(255)  

### Blood Requests
- `request_id` INT (PK)  
- `patient_name` VARCHAR(100)  
- `blood_type_needed` VARCHAR(10)  
- `quantity_needed` INT  
- `request_date` DATETIME  
- `status` VARCHAR(50)  

> Hibernate automatically manages schema mapping in Phase 2.

---

## 📌 Purpose

- Demonstrates a **full client-server system** with RMI and Hibernate  
- Shows evolution from DAO/JDBC to modern architecture  
- Academic demonstration and **portfolio-ready project**

---

