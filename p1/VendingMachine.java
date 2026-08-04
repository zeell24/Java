package clg.p1;
import java.util.Scanner;

enum Coin{
    ONE,
    TWO,
    FIVE,
    TEN
}

public class VendingMachine {
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        Coin c ;

        int SnackPrice = 15;
        int total = 0;

        System.out.println("Enter coin (ONE , TWO , FIVE , TEN): ");

        while(total < 15)
        {
            c = Coin.valueOf(cin.next().toUpperCase());
            switch(c)
            {
                case ONE -> total += 1;
                case TWO -> total += 2;
                case FIVE -> total += 5;
                case TEN -> total += 10;
                // default -> System.out.println("Enter coins from ONE , TWO , FIVE , TEN");
            }
        }

        System.out.println("Paid. Change = " + (total - SnackPrice));

        cin.close();
    }
}
