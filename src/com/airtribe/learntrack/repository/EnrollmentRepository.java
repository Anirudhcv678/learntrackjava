package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository pattern implementation for Enrollment tracking.
 * Centralizes data persistence rules for enrollments.
 */
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    /**
     * Saves an enrollment record.
     *
     * @param enrollment The Enrollment to add.
     */
    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    /**
     * Retrieves all enrollments directly belonging to a specific student ID.
     *
     * @param studentId The ID of the student.
     * @return A list containing the matched enrollments.
     */
    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Returns an enrollment matching the exact enrollment ID.
     *
     * @param id The Enrollment ID.
     * @return The Enrollment, or null if missing.
     */
    public Enrollment findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
