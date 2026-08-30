// > javac ChatFilter.java Driver.java
// > java Driver.java

package clg.p4.p4_2;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        String logs[] = {
            "10:05 alice Hello there" ,
            "10:10 bob Hii, how are you" ,
            "10:12 charlie hello !!" ,
            "11:00 XYZ",
            "11:12"
        };

        System.out.println("Enter the key : ");
        String key = cin.next();

        String endString = ChatFilter.filterLogs(logs, key); 
        
        System.out.println(endString);

        cin.close();
    }
}