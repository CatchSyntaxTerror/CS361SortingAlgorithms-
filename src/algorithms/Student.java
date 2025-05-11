package algorithms;

public class Student {
    double gpa;
    String lastName;
    int id;

    public Student(double gpa, String lastName, int id) {
        this.gpa = gpa;
        this.lastName = lastName;
        this.id = id;
    }

    public String toString() {
        return "[" + gpa + ", " + lastName + ", ID: " + id + "]";
    }
}