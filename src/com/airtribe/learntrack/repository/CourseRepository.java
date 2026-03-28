package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository pattern implementation for Course entities.
 * Handles all memory operations related to courses.
 */
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    /**
     * Persists a course into the list.
     *
     * @param course The Course to save.
     */
    public void save(Course course) {
        courses.add(course);
    }

    /**
     * Retrieves the entire list of courses.
     *
     * @return A list of courses.
     */
    public List<Course> findAll() {
        return courses;
    }

    /**
     * Retrieves a course matching the given ID.
     *
     * @param id The unique identifier of the course.
     * @return The Course object, or null if not found.
     */
    public Course findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
