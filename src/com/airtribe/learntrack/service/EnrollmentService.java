package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;

import java.util.List;

/**
 * Service class executing business logic for enrollments.
 */
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    /**
     * Constructor mapping required repositories and logic controllers.
     *
     * @param enrollmentRepository The data access object for enrollments.
     * @param studentService The service checking student validity.
     * @param courseService The service checking course validity.
     */
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Defaults natively wrapping the local persistence logic.
     *
     * @param studentService Native injected logic.
     * @param courseService Native injected logic for active context.
     */
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = new EnrollmentRepository();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Attempts to enroll a student ensuring all entity validation passes.
     *
     * @param enrollment The exact enrollment configuration to register.
     * @throws EntityNotFoundException if the target parent entities miss.
     */
    public void enrollStudent(Enrollment enrollment) throws EntityNotFoundException {
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

        enrollmentRepository.save(enrollment);
        System.out.println("Student " + s.getDisplayName() + " enrolled in " + c.getCourseName() + " successfully.");
    }

    /**
     * Obtains all enrollments for a respective student.
     *
     * @param studentId Formative Student ID constraint.
     * @return Relevant enrollments mapped.
     */
    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    /**
     * Rewrites status for target enrollment identifier.
     *
     * @param enrollmentId Registration target.
     * @param status Next intended enumeration status.
     * @throws EntityNotFoundException if index faults out of existence.
     */
    public void updateEnrollmentStatus(int enrollmentId, String status) throws EntityNotFoundException {
        Enrollment e = enrollmentRepository.findById(enrollmentId);
        if (e != null) {
            e.setStatus(status);
            System.out.println("Enrollment ID " + enrollmentId + " status updated to " + status);
            return;
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }
}
