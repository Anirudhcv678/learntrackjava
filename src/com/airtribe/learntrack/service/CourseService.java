package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added: " + course.getCourseName());
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(int id) throws EntityNotFoundException {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found.");
    }

    public void toggleCourseActiveStatus(int id) throws EntityNotFoundException {
        Course c = getCourseById(id);
        c.setActive(!c.isActive());
        System.out.println("Course " + c.getCourseName() + " active status is now: " + c.isActive());
    }
}
