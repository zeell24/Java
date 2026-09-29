package clg.p5.p5_2;

abstract class Employee
{
    String name;
    int id;

    Employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee
{
    double salary;

    FullTime(String name, int id, double salary)
    {
        super(name, id);
        this.salary = salary;
    }

    @Override
    double monthlySalary()
    {
        return salary;
    }
}

class PartTime extends Employee
{
    double hours;
    double rate;

    PartTime(String name, int id, double hours, double rate)
    {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    double monthlySalary()
    {
        return hours * rate;
    }
}

class Intern extends Employee
{
    double stipend;

    Intern(String name, int id, double stipend)
    {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    double monthlySalary()
    {
        return stipend;
    }
}

public class Main2 {
    public static void main(String[] args) {
        
        Employee[] employees = {
            new FullTime("Zeel", 101, 50000),
            new PartTime("Abc", 102, 80, 200),
            new Intern("xyz", 103, 10000)
        };

        double total = 0;

        for (Employee e : employees)
        {
            double salary = e.monthlySalary();

            System.out.println(
                e.name + " (" + e.id + ") : " + salary
            );

            total += salary;

            if (e instanceof Intern)
            {
                System.out.println("Note: This employee is an intern.");
            }
        }

        System.out.println("Total salary(combined of all employee): " + total);
    }
}
