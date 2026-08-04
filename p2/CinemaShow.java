package clg.p2;
import java.util.Scanner;

public class CinemaShow {

    private String title;
    private int seatsAvailable;
    private int capacity;
    private static int totalBooked = 0;

    CinemaShow(String title , int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    CinemaShow(String title)
    {
        this(title, 100);
    }

    void showInfo()
    {
        System.out.println("Title : " + title);
        System.out.println("Capacity : " + capacity);
    }

    boolean book(int n)
    {
        if(n <= seatsAvailable){
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        else
            return false;
    }

    void cancel(int n)
    {
        seatsAvailable += n;
        if(seatsAvailable > capacity)
        {
           seatsAvailable = capacity;
        }
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    int getTotalBooked()
    {
        return totalBooked;
    }

    int getCapacity()
    {
        return capacity;
    }
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        String tempString;
        System.out.println("Enter title of the show : ");
        tempString = cin.nextLine();

        int tempNo;
        System.out.println("Enter total capacity of the show : ");
        tempNo = cin.nextInt(); 

        CinemaShow c1 = new CinemaShow(tempString, tempNo);

        int choice;
        int temp;

        do
        {
            System.out.println("\n----- Operations -----");
            System.out.println("1. Book seats");
            System.out.println("2. Cancel seats");
            System.out.println("3. Show Booked and Available seats");
            System.out.println("4. Show cinema-show info");
            System.out.println("0. Exit");

            System.out.println("\nEnter your choice");
            choice = cin.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter seats to be booked : ");
                    temp = cin.nextInt();
                    c1.book(temp);
                    System.out.println("Seats available : " + c1.getSeatsAvailable());
                    break;
            
                case 2:
                    System.out.println("Enter seats to be canceled : ");
                    temp = cin.nextInt();
                    c1.cancel(temp);
                    System.out.println("Seats available : " + c1.getSeatsAvailable());
                    break;

                case 3:
                    System.out.println("Booked Seats : " + c1.getTotalBooked());
                    System.out.println("Total Seats : " + c1.getCapacity());
                    break;

                case 4:
                    c1.showInfo();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }while(choice!=0);

        cin.close();
    }
}
