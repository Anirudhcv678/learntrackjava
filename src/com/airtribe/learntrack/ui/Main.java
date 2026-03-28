package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to LearnTrack - Student & Course Management System");

        boolean exit = false;
        while (!exit) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 5:
                        addCourse();
                        break;
                    case 6:
                        viewAllCourses();
                        break;
                    case 7:
                        toggleCourseStatus();
                        break;
                    case 8:
                        enrollStudent();
                        break;
                    case 9:
                        viewStudentEnrollments();
                        break;
                    case 10:
                        updateEnrollmentStatus();
                        break;
                    case 0:
                        exit = true;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.println("5. Add New Course");
        System.out.println("6. View All Courses");
        System.out.println("7. Activate/Deactivate Course");
        System.out.println("8. Enroll Student in Course");
        System.out.println("9. View Enrollments for Student");
        System.out.println("10. Update Enrollment Status");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void addStudent() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email (or press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine();

        int id = IdGenerator.getNextStudentId();
        Student student;
        if (email.trim().isEmpty()) {
            student = new Student(id, firstName, lastName, batch);
        } else {
            student = new Student(id, firstName, lastName, email, batch);
        }
        studentService.addStudent(student);
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void searchStudentById() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentService.getStudentById(id);
            System.out.println(student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("Enter Student ID to deactivate: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            studentService.deactivateStudent(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCourse() {
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter Duration (in weeks): ");
        try {
            int duration = Integer.parseInt(scanner.nextLine());
            int id = IdGenerator.getNextCourseId();
            Course course = new Course(id, name, desc, duration);
            courseService.addCourse(course);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input for duration.");
        }
    }

    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course c : courses) {
                System.out.println(c);
            }
        }
    }

    private static void toggleCourseStatus() {
        System.out.print("Enter Course ID to toggle active status: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            courseService.toggleCourseActiveStatus(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enrollStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Course ID: ");
            int courseId = Integer.parseInt(scanner.nextLine());
            
            int enrollmentId = IdGenerator.getNextEnrollmentId();
            Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId, LocalDate.now(), "ACTIVE");
            enrollmentService.enrollStudent(enrollment);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewStudentEnrollments() {
        System.out.print("Enter Student ID: ");
        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for Student ID " + studentId);
            } else {
                for (Enrollment e : enrollments) {
                    System.out.println(e);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        }
    }

    private static void updateEnrollmentStatus() {
        System.out.print("Enter Enrollment ID to update: ");
        try {
            int enrollmentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter New Status (e.g., COMPLETED, CANCELLED): ");
            String status = scanner.nextLine();
            
            if (status.trim().isEmpty()) {
                throw new InvalidInputException("Status cannot be empty.");
            }
            enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
