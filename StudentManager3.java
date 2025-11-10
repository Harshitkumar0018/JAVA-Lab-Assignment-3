import java.io.*;
import java.util.*;

public class StudentManager3 {
    private List<Student> students = new ArrayList<>();
    private String filePath;

    public StudentManager3(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    private void loadFromFile() {
        File f = new File(filePath);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 5) continue;

                int roll = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                String course = parts[3].trim();
                double marks = Double.parseDouble(parts[4].trim());

                students.add(new Student(roll, name, email, course, marks));
            }
        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                bw.write(s.getRollNo() + "," + s.getName() + "," + s.getEmail() + "," + s.getCourse() + "," + s.getMarks());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void addStudent(Integer rollNo, String name, String email, String course, Double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        students.add(new Student(rollNo, name, email, course, marks));
    }

    public Student searchByName(String name) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student with name '" + name + "' not found.");
    }

    public void deleteByName(String name) throws StudentNotFoundException {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name.trim())) {
                it.remove();
                return;
            }
        }
        throw new StudentNotFoundException("Student with name '" + name + "' not found for deletion.");
    }

    public void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void printGrade(Student s) {
        double m = s.getMarks();
        if (m >= 90) System.out.println("Grade: A");
        else if (m >= 75) System.out.println("Grade: B");
        else if (m >= 50) System.out.println("Grade: C");
        else System.out.println("Grade: D");
    }
}
