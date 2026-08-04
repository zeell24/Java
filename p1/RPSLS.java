package clg.p1;
import java.util.Scanner;
public class RPSLS {
    enum Move{
        ROCK,
        PAPER,
        SCISSORS,
        LIZARD,
        SPOCK
    }

    public static int winner(Move a , Move b)
    {
        if(a == b) return 0;
        if(a == Move.ROCK)
        {
            if(b == Move.SCISSORS || b == Move.LIZARD)
                return 1;
        }
        else if(a == Move.PAPER)
        {
            if(b == Move.ROCK || b == Move.SPOCK)
                return 1;
        }
        else if(a == Move.SCISSORS)
        {
            if(b == Move.PAPER || b == Move.LIZARD)
                return 1;
        }
        else if(a == Move.LIZARD)
        {
            if(b == Move.SPOCK || b == Move.PAPER)
                return 1;
        }
        else if(a == Move.SPOCK)
        {
            if(b == Move.SCISSORS || b == Move.ROCK)
                return 1;
        }
        return -1;
    }
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);

        Move a;
        Move b;
        int rand;

        int playerWins = 0;
        int computerWins = 0;

        for(int i=1 ; i<=5 ; i++)
        {
            System.out.println("Game "+ i);
            System.out.println("Enter Move of a : ");
            a = Move.valueOf(cin.next().toUpperCase());
            
            rand = (int)(Math.random()*5) + 1;
            
            b = switch(rand)
            {
                case 1 -> Move.ROCK;
                case 2 -> Move.PAPER;
                case 3 -> Move.SCISSORS;
                case 4 -> Move.LIZARD;
                case 5 -> Move.SPOCK;
                default -> Move.ROCK; 
            };

            System.out.println("Computer(b) choosed " + b);
            int result = winner(a , b);

            if(result == 1){
                System.out.println("a wins");
                playerWins++;
            }
            else if(result == -1){
                System.out.println("b wins");
                computerWins++;
            }
            else if(result == 0){
                System.out.println("TIE!!");
            }
            System.out.println("---------------");
        }

        if(playerWins > computerWins)
            System.out.println("A wins!!");
        else if(playerWins < computerWins)
            System.out.println("B wins!!");
        else 
            System.out.println("It's a TIE!!");
        cin.close();
    }

}
