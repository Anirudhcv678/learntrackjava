package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing Student data in-memory.
 * Follows the Repository Design Pattern isolating data access from business logic.
 */
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    /**
     * Saves a student entity into the data store.
     *
     * @param student The student to save.
     */
    public void save(Student student) {
        students.add(student);
    }

    /**
     * Retrieves all recorded students.
     *
     * @return A list of all students.
     */
    public List<Student> findAll() {
        return students;
    }

    /**
     * Finds a student by their unique ID.
     *
     * @param id The ID to search for.
     * @return The Student if found, or null if it does not exist.
     */
    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
