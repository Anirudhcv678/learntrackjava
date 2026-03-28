# Design Notes

## Why ArrayList?
Java's `ArrayList` was chosen over regular Arrays because of its dynamic resizing capabilities. As a system scaled to handle students and courses, an unpredictable number of entities can be inserted over time. Unlike standard arrays which have a fixed length initialized upon creation, an `ArrayList` easily accommodates `add()` operations safely.

## Static Members Usage
The project implements an `IdGenerator` utility strictly relying on static variables (like `studentIdCounter`). 
* **Why Static?** We need to preserve the ID counter state independently across all objects inside the application context. By making it static, the value persists and is shared across calls like `IdGenerator.getNextStudentId()`, allowing simple and effective auto-incrementing ID creation without passing an instance object around everywhere.

## Benefits of Inheritance
We introduced the `Person` base class and extended it into a `Student` entity. 
* **What we gained:** Encapsulation and code re-usability. Core human attributes like `firstName`, `lastName`, and `email` natively belong to any person in the institute (Students, Trainers, Admins). By inheriting these values, the `Student` class is kept clean and solely defines student-specific attributes like `batch` and `active` tracking. Furthermore, this opens up polymorphism opportunities (e.g. iterating over `List<Person>` objects safely).
