package com.airtribe.learntrack.test;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.time.LocalDate;

public class EnrollmentServiceTest {
    public void testAll() throws Exception {
        testEnrollStudent();
        System.out.println("EnrollmentServiceTest: OK");
    }

    private void testEnrollStudent() throws Exception {
        StudentService ss = new StudentService();
        CourseService cs = new CourseService();
        EnrollmentService es = new EnrollmentService(ss, cs);

        ss.addStudent(new Student(1, "John", "Doe", "mail", "Batch 1"));
        cs.addCourse(new Course(1, "Test Course", "Desc", 10));

        Enrollment e = new Enrollment(1, 1, 1, LocalDate.now(), "ACTIVE");
        es.enrollStudent(e);

        if (es.getEnrollmentsForStudent(1).size() != 1) throw new RuntimeException("Failed to enroll student");
    }
}
