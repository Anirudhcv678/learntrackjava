package com.airtribe.learntrack.test;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("--- Running Unit Tests ---");
        try {
            new StudentServiceTest().testAll();
            new CourseServiceTest().testAll();
            new EnrollmentServiceTest().testAll();
            System.out.println("--- All Tests Passed Successfully ---");
        } catch (Exception e) {
            System.err.println("Test Failed! " + e.getMessage());
            e.printStackTrace();
        }
    }
}
