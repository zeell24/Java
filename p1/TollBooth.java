package clg.p1;
import java.util.Scanner;

record Vehicle(String number , String type){}

public class TollBooth
{
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int bike = 0;
        int car = 0;
        int truck = 0;

        int totalToll = 0;
        String tempType = "";
        String tempNumber = "";

        while(true)
        {
            System.out.println("Enter Number of Vehicle : ");
            tempNumber = cin.next().toLowerCase();

            if(tempNumber.equals("done"))
                break;

            System.out.println("Enter Type of Vehicle : ");
            tempType = cin.next().toLowerCase();

            Vehicle v = new Vehicle(tempNumber, tempType);

            int count = Integer.parseInt(tempNumber);
            switch (v.type()) {
                case "bike" -> {
                    bike += count;
                    totalToll += 20 * count;
                }
                case "car" -> {
                    car += count;
                    totalToll += 50 * count;
                }
                case "truck" -> {
                    truck += count;
                    totalToll += 150 * count;
                }
                default -> System.out.println();
            }
        }

        System.out.println("\nTotal Toll : " + totalToll);
        
        String Max;

        if(bike > car && bike > truck)
            Max = "bike";
        else if(car > bike && car > truck)
            Max = "car";
        else
            Max = "truck";

        System.out.println("Most frequent : " + Max);


        cin.close();
    }
    
}
