# Blood Donation Management System
### Phase 1 — DAO / Model / View

---

> **Important Context: About This Project**
>
> This project was developed using **Apache NetBeans IDE 8** (released in 2015), which is now considered a legacy version. Unlike modern development environments with automated dependency management, one-click deployment, and real-time error highlighting, NetBeans 8 requires manual configuration of libraries, JDBC drivers, and build paths.

**What this means for you:**

- The code follows older Java conventions (pre-Java 11)
- GUI forms are built with Swing using NetBeans 8's drag-and-drop designer (no manual layout coding)
- Database connections are managed manually via JDBC, not ORM tools like Hibernate (in this phase)
- The project structure follows NetBeans 8 conventions with `nbproject/` folders
- Visual appearance is functional but not modernized (no CSS, no animations, no responsive design)
- The GUI was designed using NetBeans 8's drag-and-drop form builder, where UI components are placed visually, and all business logic and event handling are written manually in the source code

This is an early version of the Blood Donation Management System built using **Java**, **JDBC**, and **DAO patterns**. This branch demonstrates the initial architecture and functionality before moving to Hibernate and RMI in the final version.

> Please set expectations accordingly — this is a learning/academic project that prioritizes understanding core concepts over modern UI/UX trends.

---

## Project Overview

This system manages donors, donations, and blood requests. It provides a GUI for users to:

- Add, update, delete, and view donors
- Record and view donations
- Handle blood requests (pending and fulfilled)
- View basic dashboard information

---

## Technologies Used

| Technology | Version | Notes |
|---|---|---|
| Apache NetBeans IDE | 8.x (2015 release) | Legacy IDE, requires manual configuration |
| Java | 8 (JDK 1.8) | Pre-modern Java version |
| JDBC | 4.2 | Manual database connectivity |
| DAO Pattern | — | For modular data access separation |
| Swing | — | Legacy GUI framework with drag-and-drop designer |
| Database | Any JDBC-compatible DB | Manual driver setup required |

## Project Structure

```
src/
  dao/        Data Access Objects for each entity (Donor, Donation, BloodRequest)
  model/      Java classes representing database entities
  view/       Swing GUI forms built with NetBeans drag-and-drop
build/        Compiled classes and temporary files
nbproject/    NetBeans project configuration (IDE-specific)
```

## Setup Instructions for NetBeans 8

1. Open **NetBeans 8** IDE
2. Open the project folder as an existing project
3. Add your **JDBC driver JAR** to the project libraries *(manual step)*
4. Configure database connection settings in the code
5. Build and run

---

## Next Phase (Final Version)

The final version of this project will include:

- Hibernate for ORM
- RMI for client-server architecture
- Improved error handling and validation
- Better separation of concerns

---

## Acknowledgments

Built as part of academic coursework using legacy tools to demonstrate fundamental Java concepts including DAO patterns, JDBC connectivity, and Swing GUI development with NetBeans drag-and-drop designer.
