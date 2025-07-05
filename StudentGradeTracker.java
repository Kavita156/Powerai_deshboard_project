import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManager {

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

        @Override
        public String toString() {
            return "Student: " + name + "\nGrades: " + grades + "\n";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student Grade Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade to Student");
            System.out.println("3. View All Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    students.add(new Student(name));
                    System.out.println("Student added.");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students available.");
                        break;
                    }

                    System.out.println("Select student:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i).name);
                    }

                    System.out.print("Enter student number: ");
                    int studentIndex = scanner.nextInt() - 1;
                    if (studentIndex < 0 || studentIndex >= students.size()) {
                        System.out.println("Invalid student number.");
                        break;
                    }

                    System.out.print("Enter grade (0-100): ");
                    int grade = scanner.nextInt();
                    if (grade < 0 || grade > 100) {
                        System.out.println("Invalid grade.");
                        break;
                    }

                    students.get(studentIndex).addGrade(grade);
                    System.out.println("Grade added.");
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

     


