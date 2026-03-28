package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getDisplayName());
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) throws EntityNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student s = getStudentById(id);
        s.setActive(false);
        System.out.println("Student " + s.getDisplayName() + " deactivated.");
    }
}
