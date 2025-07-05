import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeTracker {

    // Student class with grade methods
    static class Student {
        String name;
        ArrayList<Integer> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        void addGrade(int grade) {
            grades.add(grade);
        }

        double getAverage() {
            if (grades.isEmpty()) return 0.0;
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return (double) sum / grades.size();
        }

        int getHighest() {
            return grades.isEmpty() ? 0 : Collections.max(grades);
        }

        int getLowest() {
            return grades.isEmpty() ? 0 : Collections.min(grades);
        }

        @Override
        public String toString() {
            return "Name: " + name +
                   "\nGrades: " + grades +
                   "\nAverage: " + String.format("%.2f", getAverage()) +
                   "\nHighest: " + getHighest() +
                   "\nLowest: " + getLowest() + "\n";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade to Student");
            System.out.println("3. Display Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    students.add(new Student(name));
                    System.out.println("Student added.");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found. Add a student first.");
                        break;
                    }
                    System.out.println("Select a student:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i).name);
                    }
                    int index = scanner.nextInt() - 1;
                    if (index < 0 || index >= students.size()) {
                        System.out.println("Invalid selection.");
                        break;
                    }

                    System.out.print("Enter grade (0–100): ");
                    int grade = scanner.nextInt();
                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100.");
                        break;
                    }

                    students.get(index).addGrade(grade);
                    System.out.println("Grade added.");
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No student data available.");
                    } else {
                        System.out.println("\n--- Summary Report ---");
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
