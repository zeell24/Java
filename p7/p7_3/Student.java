package clg.p7.p7_3;

public class Student {
    @Column(name = "student_name")
    String name;

    @Column(name = "student_id")
    int id;

    void displayStudent()
    {
        System.out.println("Student:");
        System.out.println("Name: " + name);
        System.out.println("ID:   " + id);
    }
}
