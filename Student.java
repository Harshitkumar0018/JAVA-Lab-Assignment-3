public class Student {
    private int rollNo;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
               "\nName: " + name +
               "\nEmail: " + email +
               "\nCourse: " + course +
               "\nMarks: " + marks + "\n";
    }
}
