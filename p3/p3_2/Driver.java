// to run in terminal
//     > javac Card.java Driver.java
//     > java Driver.java

package clg.p3.p3_2;

// import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        
        Card[] card = new Card[10];

        Card[] input = {
                
            new Card("Ace" , "Spade") ,
            new Card("Queen", "Heart") ,
            new Card("Ace" , "Spade")
        };

        int count = 0;

        for(int i=0 ; i < input.length ; i++)
        {
            boolean isDuplicate = false;

            for(int j=i+1 ; j < input.length ; j++)
            {
                if(input[i].equals(input[j]))
                {
                    isDuplicate = true;
                    System.out.println("Duplicate found: " + input[i]);
                    break;
                }
            }

            if(!isDuplicate){
                card[count++] = input[i];
                count++;
            }
                
        }

    }

}
