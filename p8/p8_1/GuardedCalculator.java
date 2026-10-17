package clg.p8.p8_1;
import java.util.Scanner;

public class GuardedCalculator {
    public static void main(String[] args) 
    {
        Scanner cin = new Scanner(System.in);
        boolean success = false;
        int attempt = 0;

        while (!success) 
        {
            attempt++;

            try 
            {
                System.out.println("Enter first no. : ");
                String ip1 = cin.next();

                System.out.println("Enter second no. : ");
                String ip2 = cin.next();

                System.out.println("Enter the operator : ");
                String op = cin.next();
                
                System.out.println("Input : ");
                System.out.println(ip1 + op + ip2);

                double a = Double.parseDouble(ip1);
                double b = Double.parseDouble(ip2);
                switch (op) 
                {
                    case "+":
                        System.out.println("Result: " + (a + b));
                        success = true;
                        break;

                    case "-":
                        System.out.println("Result: " + (a - b));
                        success = true;
                        break;

                    case "*":
                        System.out.println("Result: " + (a * b));
                        success = true;
                        break;

                    case "/":
                        if (b == 0) {
                            throw new DividedByZeroException("Cannot divide by zero!");
                        }
                        System.out.println("Result: " + (a / b));
                        success = true;
                        break;

                    default:
                        System.out.println("Invalid operator!");
                }

                System.out.println("----------");

            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number entered!");
            }
            catch (DividedByZeroException e) {
                System.out.println(e.getMessage());
            }
            finally {
                System.out.println("Attempt " + attempt + " logged.");
            }
        }
        
        System.out.println("Exiting...");
        cin.close();
    }
}
