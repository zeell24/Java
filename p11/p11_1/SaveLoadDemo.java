package clg.p11.p11_1;

import java.io.*;

public class SaveLoadDemo
{
    public static void main(String[] args) 
    {
        Student[] students = {
            new Student(1, "Zeel", "passZeel"),
            new Student(2, "Tinisha", "passTini"),
            new Student(3, "xyz", "passXyz")
        };

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) 
        {
            out.writeObject(students);
            System.out.println("Objects saved.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            Student[] loaded = (Student[]) in.readObject();

            for(Student s : loaded)
            {
                System.out.println(s.id + " " + s.name
                        + " Password: " + s.password);
            }
        }
        catch(IOException | ClassNotFoundException e)
        {  
            e.printStackTrace();
        }
    }
}