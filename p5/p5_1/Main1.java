package clg.p5.p5_1;

abstract class Shape
{
    abstract double area(); 

}

class Circle extends Shape
{
    double r;

    Circle(double radius)
    {
        this.r = radius;
    }

    @Override
    double area()
    {
        return 3.14 * r * r;
    }
}

class Rectangle extends Shape
{
    double l;
    double b;

    Rectangle(double length , double breadth)
    {
        this.l = length;
        this.b = breadth;
    }

    @Override 
    double area()
    {
        return l * b;
    }
}

class Triangle extends Shape
{
    double b;
    double h;

    Triangle(double base , double height)
    {
        this.b = base;
        this.h = height;
    }

    @Override
    double area()
    {
        return (0.5) * b * h;
    }
}

public class Main1 {
    public static void main(String[] args) {
     
        Shape[] shapes = {
            new Circle(7),
            new Triangle(3,2),
            new Rectangle(4,5)
        };

        double total = 0;
        double largest = 0;

        for(Shape s : shapes)
        {
            double area = s.area();
            System.out.println("Area : " + area);

            total += area;

            if(area > largest)
            {
                largest = area;
            }
        }

        System.out.println("Total area : " + total);
        System.out.println("Largest area : " + largest);

    }
}
