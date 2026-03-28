package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;

import java.util.List;

/**
 * Service class handling Course business logic.
 * Uses CourseRepository designed via the Repository Pattern.
 */
public class CourseService {
    private final CourseRepository courseRepository;

    /**
     * Initializes the service with a course repository dependency.
     *
     * @param courseRepository The course data store.
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Initializes the default CourseService instantiating its own dependency natively.
     */
    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    /**
     * Registers a new course.
     *
     * @param course The course entity.
     */
    public void addCourse(Course course) {
        courseRepository.save(course);
        System.out.println("Course added: " + course.getCourseName());
    }

    /**
     * Retrieves all available courses.
     *
     * @return A list of all Course objects.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Fetches a single course by its unique ID.
     *
     * @param id The identifier for the course.
     * @return The Course entity.
     * @throws EntityNotFoundException if missing.
     */
    public Course getCourseById(int id) throws EntityNotFoundException {
        Course c = courseRepository.findById(id);
        if (c != null) {
            return c;
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found.");
    }

    /**
     * Toggles the active status of a corresponding course.
     *
     * @param id The dynamic ID of the course.
     * @throws EntityNotFoundException if missing.
     */
    public void toggleCourseActiveStatus(int id) throws EntityNotFoundException {
        Course c = getCourseById(id);
        c.setActive(!c.isActive());
        System.out.println("Course " + c.getCourseName() + " active status is now: " + c.isActive());
    }
}
