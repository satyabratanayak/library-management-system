# 📚 Library Management System (Java - OOP + SOLID)

A clean **Low-Level Design (LLD)** implementation of a Library Management System built in **Java**, demonstrating strong adherence to **OOP concepts**, **SOLID principles**, and common **design patterns**.

---

## 🧩 Overview

This project models a simple yet extensible **Library Management System** that allows librarians to:

- Manage books (add, update, search, delete)
- Manage patrons (add, update, view)
- Handle lending operations (checkout, return)
- Maintain inventory consistency and borrowing history

---

## 🏗️ System Architecture

The project follows a **3-Layered Architecture**:

1. **Model Layer** – Core domain entities (`Book`, `Patron`, `LendingRecord`)
2. **Repository Layer** – Handles in-memory storage and CRUD operations
3. **Service Layer** – Encapsulates business logic and orchestrates repositories

---


## 💡 Design Principles

| Principle | Description |
|------------|-------------|
| **Single Responsibility** | Each class has one well-defined responsibility |
| **Open/Closed** | Easily extensible for new features (e.g., search strategies) |
| **Dependency Inversion** | Services depend on interfaces, not concrete implementations |

---

## ⚙️ Design Patterns Used

| Pattern | Purpose |
|----------|----------|
| **Repository Pattern** | Abstracts data operations from business logic |
| **Strategy Pattern** | Enables extensible search logic (by title, author, ISBN) |

---

## 🧭 UML Class Diagram

![Library Management UML](https://github.com/user-attachments/assets/16e523e6-d0ca-41ea-8d93-5163d4a01bd5)

### Relationship Highlights:
- **Repository** interface realized by `BookRepository`, `PatronRepository`, and `LendingRepository`
- **Aggregation** between `Patron` and `LendingRecord`
- **Dependency** from services to repositories
- **Associations** between entities via `isbn` and `memberId`

---

## 🧰 Tech Stack
- **Language:** Java 21+
- **IDE:** IntelliJ IDEA
- **Logging:** `java.util.logging.Logger`

---

## 🚀 How to Run

1. **Clone this repository**
   ```bash
   git clone https://github.com/your-username/library-management-system.git
   cd library-management-system
2. **Open in IntelliJ IDEA**
   - Ensure **Project SDK = Java 21**
   - Mark `src` as a **Sources Root**

3. **Run the Project**
   - Open `Main.java`
   - Click ▶️ **Run**

4. **Expected Output**
   Console logs showing:
   ```bash
    Oct 19, 2025 1:34:28 AM com.lms.Main main
    INFO: Search by author 'martin': [Patron{memberId='P1', name='Alice', email='alice@example.com', historyCount=0}, Patron{memberId='P2', name='Bob', email='bob@example.com', historyCount=0}]
    Oct 19, 2025 1:34:28 AM com.lms.Main main
    INFO: Search by author 'martin': [Book{isbn='ISBN-001', title='Clean Code', author='Robert C. Martin', year=2008, total=3, available=3}]
    Oct 19, 2025 1:34:28 AM com.lms.service.LendingService checkout
    INFO: Checked out ISBN=ISBN-001 to member=P1 recordId=b923bab2-19d7-43ca-b221-9f2146cdf942
    Oct 19, 2025 1:34:28 AM com.lms.Main main
    INFO: Checkout record: LendingRecord{recordId='b923bab2-19d7-43ca-b221-9f2146cdf942', isbn='ISBN-001', memberId='P1', issueDate=2025-10-20, dueDate=2025-11-03, returnDate=null}
    Oct 19, 2025 1:34:28 AM com.lms.service.LendingService returnBook
    INFO: Returned recordId=b923bab2-19d7-43ca-b221-9f2146cdf942 isbn=ISBN-001
    Oct 19, 2025 1:34:28 AM com.lms.Main lambda$main$3
    INFO: Book: Book{isbn='ISBN-002', title='Effective Java', author='Joshua Bloch', year=2018, total=2, available=2}
    Oct 19, 2025 1:34:28 AM com.lms.Main lambda$main$3
    INFO: Book: Book{isbn='ISBN-001', title='Clean Code', author='Robert C. Martin', year=2008, total=3, available=3}
    Oct 19, 2025 1:34:28 AM com.lms.Main main
    INFO: Search by author 'martin': [Patron{memberId='P1', name='Alice', email='newalice@example.com', historyCount=0}, Patron{memberId='P2', name='Bob', email='bob@example.com', historyCount=0}]
    ```
