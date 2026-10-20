package clg.p14.p14_2.src.com.example.app;

import clg.p14.p14_2.src.com.example.model.Item;
import clg.p14.p14_2.src.com.example.service.ItemService;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ItemService service = new ItemService();
        Scanner cin = new Scanner(System.in);

        while(true)
        {
            System.out.println("1. Add item");
            System.out.println("2. Show count");
            System.out.println("3. Save data");
            System.out.println("4. Load data");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int choice = cin.nextInt();
            cin.nextLine();

            if(choice == 1)
            {
                System.out.print("Enter item name: ");
                service.addItem(new Item(cin.nextLine()));
            }
            else if(choice == 2)
            {
                System.out.println("Count: " + service.getCount());
            }
            else if(choice == 3)
            {
                service.save("items.txt");
                System.out.println("Data saved.");
            }
            else if(choice == 4)
            {
                service.load("items.txt");
                System.out.println("Data loaded.");
            }
            else if(choice == 5)
            {
                break;
            }
            else
            {
                System.out.println("Invalid choice.");
            }

            System.out.println("-------------");
        }

        cin.close();
    }
}