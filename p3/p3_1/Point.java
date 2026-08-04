package clg.p3.p3_1;
// import java.util.Scanner;

public class Point
{

    private int x;
    private int y;

    Point(int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + x + " , " + y + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || !(obj instanceof Point)) return false;

        Point p = (Point)obj;

        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(x,y);
    }

    
}
