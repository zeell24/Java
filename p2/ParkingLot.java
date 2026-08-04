package clg.p2;
import java.util.Scanner;

public class ParkingLot
{
    private int twoWheelers = 0;
    private int fourWheelers = 0;

    private final int twoCap;
    private final int fourCap;

    private static long revenue;

    ParkingLot(int twoCap , int fourCap)
    {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    void park(String type)
    {
        if(type.equals("two"))
        {
            if(twoWheelers == twoCap)
            {
                System.out.println("Parking is full for 2 wheelers!");
            }
            else {
                twoWheelers++;
                revenue += 20;
            }
        }
        else if(type.equals("four"))
        {
            if(fourWheelers == fourCap)
            {
                System.out.println("Parking is full for 4 wheelers!");
            }
            else {
                fourWheelers++;
                revenue += 40;
            }
        }
    }

    void leave(String type)
    {
        if(type.equals("two"))
        {
            if(twoWheelers > 0)
            {
                twoWheelers--;
                System.out.println("Two wheeler left!");
            }
        }
        else if(type.equals("four"))
        {
            if(fourWheelers > 0)
            {
                fourWheelers--;
                System.out.println("Four wheeler left!");
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        ParkingLot p = new ParkingLot(20, 20);
        System.out.println("Parking two wheeler ...");
        p.park("two");

        System.out.println("Parking four wheeler ...");
        p.park("four");
        p.leave("two");
        p.leave("four");
        
        System.out.println("Total revenue : " + revenue);

        cin.close();
    }
}