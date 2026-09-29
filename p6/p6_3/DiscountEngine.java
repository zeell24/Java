package clg.p6.p6_3;

import java.util.Scanner;

public class DiscountEngine
{
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        double[] prices = {500, 1000, 1500, 2000};

        DiscountRule rule;

        System.out.println("Discount Options :");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs.100 Discount");

        System.out.print("\nEnter your choice : ");
        int choice = cin.nextInt();

        switch(choice)
        {
            case 1:
                rule = price -> price * 0.90;
                break;

            case 2:
                rule = price -> price * 0.80;
                break;

            case 3:
                rule = price -> price - 100;
                break;

            default:
                System.out.println("Invalid Choice");
                cin.close();
                return;
        }

        System.out.println("\nPrices after Discount :");

        for(double price : prices)
        {
            double finalPrice = rule.apply(price);

            System.out.println(
                "Original : Rs." + price +
                "  ->  Final : Rs." + finalPrice
            );
        }

        cin.close();
    }
}