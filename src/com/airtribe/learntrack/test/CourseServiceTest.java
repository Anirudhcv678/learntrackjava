package com.airtribe.learntrack.test;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.service.CourseService;

public class CourseServiceTest {
    public void testAll() throws Exception {
        testAddCourse();
        testToggleCourse();
        System.out.println("CourseServiceTest: OK");
    }
    
    private void testAddCourse() throws Exception {
        CourseService service = new CourseService();
        Course c = new Course(1, "Java", "Desc", 4);
        service.addCourse(c);
        if (service.getAllCourses().size() != 1) throw new RuntimeException("Failed to add course");
        if (service.getCourseById(1).getDurationInWeeks() != 4) throw new RuntimeException("Data mismatch");
    }

    private void testToggleCourse() throws Exception {
        CourseService service = new CourseService();
        Course c = new Course(2, "Spring", "Desc", 6);
        service.addCourse(c);
        service.toggleCourseActiveStatus(2);
        if (service.getCourseById(2).isActive() != false) throw new RuntimeException("Failed to toggle status");
    }
}
