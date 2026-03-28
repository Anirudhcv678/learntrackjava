package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private List<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(Enrollment enrollment) throws EntityNotFoundException {
        // Validation: verify student and course exist
        Student s = studentService.getStudentById(enrollment.getStudentId());
        Course c = courseService.getCourseById(enrollment.getCourseId());
        
        if (!s.isActive()) {
            System.out.println("Cannot enroll an inactive student.");
            return;
        }
        if (!c.isActive()) {
            System.out.println("Cannot enroll in an inactive course.");
            return;
        }

        enrollments.add(enrollment);
        System.out.println("Student " + s.getDisplayName() + " enrolled in " + c.getCourseName() + " successfully.");
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public void updateEnrollmentStatus(int enrollmentId, String status) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == enrollmentId) {
                e.setStatus(status);
                System.out.println("Enrollment ID " + enrollmentId + " status updated to " + status);
                return;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }
}
