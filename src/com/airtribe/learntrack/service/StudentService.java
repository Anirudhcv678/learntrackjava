package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;

import java.util.List;

/**
 * Service class responsible for Student business logic.
 * Interacts with the StudentRepository for data access operations.
 */
public class StudentService {
    private final StudentRepository studentRepository;

    /**
     * Constructs a StudentService with the given repository.
     *
     * @param studentRepository The repository for student data.
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Constructs a StudentService defaulting to a new repository context.
     */
    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    /**
     * Adds a new student to the system.
     *
     * @param student The student entity to add.
     */
    public void addStudent(Student student) {
        studentRepository.save(student);
        System.out.println("Student added: " + student.getDisplayName());
    }

    /**
     * Retrieves all students registered in the system.
     *
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Finds a student by their ID.
     *
     * @param id The ID to look up.
     * @return The found Student.
     * @throws EntityNotFoundException if the student does not exist.
     */
    public Student getStudentById(int id) throws EntityNotFoundException {
        Student s = studentRepository.findById(id);
        if (s != null) {
            return s;
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    /**
     * Deactivates a student by setting their active status to false.
     *
     * @param id The ID of the student to deactivate.
     * @throws EntityNotFoundException if the student does not exist.
     */
    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student s = getStudentById(id);
        s.setActive(false);
        System.out.println("Student " + s.getDisplayName() + " deactivated.");
    }
}
