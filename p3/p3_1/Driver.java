// to run in terminal
//     > javac Point.java Driver.java
//     > java Driver.java

package clg.p3.p3_1;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        Point p[] = {
            new Point(2, 3) ,
            new Point(3, 1) ,
            new Point(10, 11) ,
            new Point(10, 11) ,
            new Point(2, 7) ,
            new Point(12, 3)
        };

        int distinct = 0;
        int totalDuplicates = 0;

        for(int i=0 ; i < p.length ; i++)
        {
            boolean isDuplicate = false;

            for(int j=i+1 ; j < p.length ; j++)
            {
                if(p[i].equals(p[j]))
                {
                    isDuplicate = true;
                    totalDuplicates++;
                    break;
                }
            }

            if(!isDuplicate)
                distinct++;
        }



        System.out.println("Distinct : " + distinct);
        System.out.println("Total Duplicates : " + totalDuplicates);

        cin.close();
    }
}
