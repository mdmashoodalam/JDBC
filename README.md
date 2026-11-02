# Student Records | JDBC Practice

A small Java console project for practicing **CRUD operations with JDBC and MySQL**. It keeps the database work in a repository class and maps each database row to a `Student` object.

## What it does

- Create a student record
- Update a student's name, email, and course
- Delete a student by ID
- Find one student by ID
- List all students
- Use prepared statements and try-with-resources for database access

## Built with

- Java 21
- Maven
- MySQL
- MySQL Connector/J 9.5.0

## Project layout

```text
src/main/java/org/example/
├── Main.java                         # Console entry point
├── Model/Student.java                # Student data model
└── Repository/StudentRepository.java # JDBC CRUD operations
```

## Get started

### 1. Create the database and table

Run this SQL in MySQL:

```sql
CREATE DATABASE jdbc_practice;
USE jdbc_practice;

CREATE TABLE students (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

### 2. Configure your connection

`StudentRepository` currently connects to `jdbc:mysql://localhost:3306/jdbc_practice` with the MySQL user `root` and password `root`. Update these values in `src/main/java/org/example/Repository/StudentRepository.java` to match your local MySQL setup.

### 3. Build and run

From the project directory:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

The project does not currently declare the Maven Exec plugin. To run from IntelliJ IDEA, open the project as a Maven project and run `org.example.Main`. You can also compile with Maven and launch the main class from your IDE.

## Try the operations

`Main.java` currently calls `getStudent()` to print all rows. The other repository methods are available to call from `main`:

```java
StudentRepository students = new StudentRepository();

students.createStudent(new Student("Sam Lee", "sam@example.com", "Computer Science"));
students.getStudentById(1);
students.updateStudent(new Student("Sam Lee", "sam@example.com", "Data Science"), 1);
students.deleteStudent(1);
students.getStudent();
```

Run only the operations you need; create, update, and delete change the database.

## How it is organized

- `Student` holds an ID, name, email, and course.
- `StudentRepository` contains the SQL and converts result rows into `Student` instances.
- `Main` is the simple entry point for trying repository operations.

Prepared statements keep values separate from SQL text, and try-with-resources closes connections, statements, and result sets after each operation.

## Notes

- The database and table must exist before running the application.
- The connection settings are local development defaults; use credentials that match your MySQL installation.
- This is a learning project. It prints database errors to the console and does not include a UI or automated tests.
"# JDBC" 
