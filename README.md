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

---

## 🏗 Phase 2 — Final Version (Default Branch)

**Technologies Used:**  
- Java (Swing for GUI)  
- Hibernate ORM  
- Java RMI for client-server communication  
- JavaMail (optional OTP/email feature)  
- MySQL / MariaDB  
- GitHub for version control


 ## ✨ Key Features

- **User Authentication** (Login & Registration)  
- **Donor Management** – Add, edit, delete, and view donors  
- **Donation Recording** – Track blood donations with donor linkage  
- **Blood Request Management** – Hospitals/Patients request blood  
- **Blood Inventory Tracking** – Automatically updates stock based on donations and requests  
- **PDF Export** – Generate reports for records  
- **RMI Integration** – Distributed system with server-client architecture  
- **Hibernate ORM** – Database persistence with annotations and entity relationships  


---

## 🖼 Screenshots

### 🔑 Login
<img width="470" height="739" alt="Image" src="https://github.com/user-attachments/assets/3cf5db03-8678-4aac-8aff-5483d1f5977a" />

### 📝 Register
<img width="452" height="653" alt="Image" src="https://github.com/user-attachments/assets/6cc3ed85-fa29-4288-9396-1ac1609d7f5b" />

### 🧑‍🤝‍🧑 Donor Management
Manage donor records with full CRUD functionality.  
<img width="1699" height="1003" alt="Image" src="https://github.com/user-attachments/assets/bd8c006d-bf26-4c13-8cc7-c2a309a2ceb4" />

### 💉 Donation Recording
Record new blood donations and update inventory.  
<img width="1698" height="1002" alt="Image" src="https://github.com/user-attachments/assets/ae78de6e-ea0b-4334-864d-3796f8a74611" />

### 📥 Blood Request
Request blood units and track status (pending/fulfilled).  
<img width="1697" height="1001" alt="Image" src="https://github.com/user-attachments/assets/60166d58-ccc1-4edd-9e79-322d86b68fde" />

---


**Project Structure:**  
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

