package clg.p14.p14_1;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        ItemService service = new ItemService();

        while(true)
        {
            System.out.println("1. Add item");
            System.out.println("2. Show count");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = cin.nextInt();
            cin.nextLine();

            if(choice == 1)
            {
                System.out.print("Enter item name: ");
                String name = cin.nextLine();
                service.addItem(new Item(name));
                System.out.println("Item added!");
            }
            else if(choice == 2)
            {
                System.out.println("Count: " + service.getCount());
            }
            else if(choice == 0)
            {
                break;
            }
            else
            {
                System.out.println("Invalid choice!");
            }

            System.out.println("-------------");
        }

        cin.close();
    }
}