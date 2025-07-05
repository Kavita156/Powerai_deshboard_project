import java.util.ArrayList;
import java.util.Scanner;

// Class to store student data
class Student {
    String name;
    ArrayList<Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public void printGrades() {
        System.out.println("Grades for " + name + ": " + grades);
        System.out.println("Average Grade: " + getAverageGrade());
    }
}

// Main class
public class StudentGradeTracker {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Grade Tracker Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade to Student");
            System.out.println("3. View Student Grades");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> viewGrades();
                case 4 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added successfully.");
    }

    static void addGrade() {
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Enter grade to add: ");
        int grade = Integer.parseInt(scanner.nextLine());
        student.addGrade(grade);
        System.out.println("Grade added successfully.");
    }

    static void viewGrades() {
        Student student = findStudent();
        if (student != null) {
            student.printGrades();
        }
    }

    static Student findStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                return s;
            }
        }
        System.out.println("Student not found.");
        return null;
    }
}
