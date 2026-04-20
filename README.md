# Library Borrowing System
demo link：https://zoom.us/clips/share/7k3DatwjT3mU8OSf3FJqOA

A full-featured library management system built with Spring Boot and H2 database. This system allows users to search, borrow, and return books, while administrators can manage books and members.

## Features

### User Features
- User registration and login
- Search books by title or author
- Borrow books (max 5 books, 14-day loan period)
- Return books
- Save books to personal list
- View borrowing history
- Track due dates with color-coded reminders
- View overdue fee estimates
- Multi-language support (English/Chinese)
- Forgot password functionality

### Admin Features
- Add, edit, and delete books
- Manage members (view, edit, delete)
- View all borrow records
- Track overdue books
- Dashboard with statistics
- Reset member passwords

## Tech Stack

- Java 17
- Spring Boot 3.1.5
- Spring MVC
- Spring Data JPA
- H2 Database (Embedded/File-based)
- Thymeleaf
- HTML5/CSS3
- Maven

---

## 📘 For IntelliJ IDEA Users

### Step 1: Clean Up the Project Folder

**IMPORTANT:** Before opening the project in IntelliJ, you need to delete unnecessary files.

1. Navigate to your project folder
2. **DELETE** the following files/folders (if they exist):
   - `.venv/` (Python virtual environment)
   - `Include/`
   - `Lib/`
   - `Scripts/`
   - `pyvenv.cfg`
   - `CACHEDIR.TAG`
   - `schema.sql`
   - `data.sql`
   - Any Python-related files (`.py`, `.pyc`)

3. **KEEP ONLY** the folder named **`libraryborrowingsystem`** (or `library-borrowing-system`)

### Step 2: Open the Project in IntelliJ

1. Open IntelliJ IDEA
2. Click **File → Open**
3. Navigate to and select the **`libraryborrowingsystem`** folder
4. Click **OK**

### Step 3: Let IntelliJ Configure the Project

- IntelliJ will automatically detect the `pom.xml` file
- Wait for Maven to download all dependencies
- If prompted, click **"Load Maven Project"**

### Step 4: Run the Application

**Option A: Run from IntelliJ**
1. Navigate to `src/main/java/hkmu/wadd/library_borrowing_system/`
2. Right-click on `LibraryBorrowingSystemApplication.java`
3. Select **Run 'LibraryBorrowingSystemApplication.main()'**

**Option B: Run using Maven**
- Open Terminal in IntelliJ
- Run the command:
```bash
./mvnw spring-boot:run
