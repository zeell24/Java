// > javac PasswordChecker.java Driver.java
// > java Driver.java

package clg.p4.p4_1;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        String password;
        System.out.println("Enter password : ");
        password = cin.next();

        int count_rule = 0;
        PasswordChecker pwChecker = new PasswordChecker();

        if(pwChecker.checkLength(password))
            count_rule++;
        if(pwChecker.checkUpperCase(password))
            count_rule++;
        if(pwChecker.checkDigit(password))
            count_rule++;
        if(pwChecker.checkSpecialChar(password))
            count_rule++;

        String result ;
        result = switch(count_rule) {
            case 0 -> "WEAK";
            case 1 -> "WEAK";
            case 2 -> "MEDIUM";
            case 3 -> "MEDIUM";
            case 4 -> "STRONG";
            default -> "INVALID";
        };

        System.out.println("Password is " + result);

        cin.close();
    }

}
