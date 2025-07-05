import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public String getSummary() {
        return "Name: " + name +
               "\nGrades: " + (grades.isEmpty() ? "None" : grades.toString()) +
               "\nAverage: " + String.format("%.2f", getAverage()) + "\n";
    }
}

public class StudentGradeTrackerGUI extends JFrame {
    private final ArrayList<Student> students = new ArrayList<>();
    private final JTextArea outputArea = new JTextArea(10, 40);

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addStudentBtn = new JButton("Add Student");
        JButton addGradeBtn = new JButton("Add Grade");
        JButton viewStudentBtn = new JButton("View Student Grades");
        JButton viewAllBtn = new JButton("Summary Report");

        buttonPanel.add(addStudentBtn);
        buttonPanel.add(addGradeBtn);
        buttonPanel.add(viewStudentBtn);
        buttonPanel.add(viewAllBtn);

        // Output area
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addStudentBtn.addActionListener(e -> addStudent());
        addGradeBtn.addActionListener(e -> addGrade());
        viewStudentBtn.addActionListener(e -> viewStudentGrades());
        viewAllBtn.addActionListener(e -> displayAllSummaries());
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog(this, "Enter student name:");
        if (name != null && !name.trim().isEmpty()) {
            students.add(new Student(name.trim()));
            outputArea.append("Added student: " + name + "\n");
        }
    }

    private void addGrade() {
        String name = JOptionPane.showInputDialog(this, "Enter student name:");
        Student student = findStudentByName(name);
        if (student != null) {
            String gradeStr = JOptionPane.showInputDialog(this, "Enter grade:");
            try {
                int grade = Integer.parseInt(gradeStr);
                student.addGrade(grade);
                outputArea.append("Added grade " + grade + " for " + name + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid grade input.\n");
            }
        } else {
            outputArea.append("Student not found.\n");
        }
    }

    private void viewStudentGrades() {
        String name = JOptionPane.showInputDialog(this, "Enter student name:");
        Student student = findStudentByName(name);
        if (student != null) {
            outputArea.append(student.getSummary() + "\n");
        } else {
            outputArea.append("Student not found.\n");
        }
    }

    private void displayAllSummaries() {
        if (students.isEmpty()) {
            outputArea.append("No students available.\n");
            return;
        }
        outputArea.append("---- All Student Summaries ----\n");
        for (Student s : students) {
            outputArea.append(s.getSummary() + "\n");
        }
    }

    private Student findStudentByName(String name) {
        if (name == null) return null;
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeTrackerGUI tracker = new StudentGradeTrackerGUI();
            tracker.setVisible(true);
        });
    }
}
