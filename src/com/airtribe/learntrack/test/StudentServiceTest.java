package com.airtribe.learntrack.test;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.service.StudentService;

public class StudentServiceTest {
    public void testAll() throws Exception {
        testAddAndGetStudent();
        testDeactivateStudent();
        System.out.println("StudentServiceTest: OK");
    }
    
    private void testAddAndGetStudent() throws Exception {
        StudentService service = new StudentService();
        Student s = new Student(1, "Test", "User", "test@test.com", "Batch1");
        service.addStudent(s);
        if (service.getAllStudents().size() != 1) throw new RuntimeException("Size should be 1");
        if (!service.getStudentById(1).getFirstName().equals("Test")) throw new RuntimeException("Name mismatch");
    }

    private void testDeactivateStudent() throws Exception {
        StudentService service = new StudentService();
        Student s = new Student(2, "Test2", "User2", null, "Batch1");
        service.addStudent(s);
        service.deactivateStudent(2);
        if (service.getStudentById(2).isActive() != false) throw new RuntimeException("Status should be inactive");
    }
}
