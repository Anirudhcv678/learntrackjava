<div align="center">
  # LearnTrack 📚
  <p><i>A console-based Student & Course Management System built entirely with Core Java.</i></p>
  
  ![Java Version](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  ![Architecture](https://img.shields.io/badge/Architecture-Repository_Pattern-blue?style=for-the-badge)
</div>

## 📖 Overview
LearnTrack is a foundational OOP-driven project designed to track students, courses, and active enrollments using in-memory `ArrayList` data structures without relying on heavy frameworks. 

Recently refactored to implement the **Repository Design Pattern** separating raw data logic from the operational `Services` layer!

## ✨ Key Features
- **Student Data Management:** Add, search, and securely deactivate institute students.
- **Course Catalog Management:** Add, view, and toggle visibility parameters for available courses.
- **Dynamic Enrollments:** Map students directly to available courses enforcing state validation workflows safely.

## 🚀 Getting Started

### Prerequisites
- JDK 11 or higher installed.

### Build Instructions
Clone the repository and compile the native files via:
```bash
javac -d out $(find src -name "*.java")
```

### Run Instructions
Start the interactive application from the `out` distribution directory:
```bash
java -cp out com.airtribe.learntrack.ui.Main
```

## 🏗️ Architecture Design (UML)

```mermaid
classDiagram
    class Person {
        -int id
        -String firstName
        -String lastName
        -String email
        +getDisplayName()
    }
    
    class Student {
        -String batch
        -boolean active
    }
    
    class Course {
        -int id
        -String courseName
        -int durationInWeeks
    }
    
    class Enrollment {
        -int id
        -int studentId
        -int courseId
        -String status
    }
    
    class StudentRepository {
        +save(Student)
        +findAll()
    }

    class StudentService {
        +addStudent(Student)
    }

    Person <|-- Student : Inheritance
    StudentService --> StudentRepository : Repository Pattern
    Student "1" -- "*" Enrollment : Enrolls in
    Course "1" -- "*" Enrollment : Includes
```
