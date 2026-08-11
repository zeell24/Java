// > javac MiniBank.java Account.java Customer.java
// > java MiniBank.java

package clg.Bank;
import java.util.Scanner;

record BankInfo(String name , String branch){}

public class MiniBank {

    enum MenuOption
    {
        OPEN_ACCOUNT ,
        DEPOSIT , 
        WITHDRAW ,
        TRANSFER ,
        EXIT
    }

    public static void main(String args[])
    {
        Scanner cin =  new Scanner(System.in);

        //p2
        Account acc[] = new Account[2];

        acc[0] = new Account("Zeel", 25000);
        acc[1] = new Account("Diya");

        System.out.println("Withdrawing 200 from acc[0] ...");
        acc[0].Withdraw(200);
        System.out.println("Depositing 1000 into acc[1] ...");
        acc[1].Deposit(1000);

        System.out.println("Checking...");
        for(Account account : acc)
        {
            System.out.println("---");
            System.out.println("Account number : " + account.getAccountNumber());
            System.out.println("Acount holder name : " + account.getOwnerName());
            System.out.println("Account Balance : " + account.getBalance());
        }

        //p3
        System.out.println("\nChecking if acc[0] and acc[1] is equal : " + acc[0].equals(acc[1]));

        if(acc[0] instanceof Account)
            System.out.println("acc[0] ia an Account");

        //p1
        int choice = 0;
        System.out.println(" ");
        System.out.println("-------- Virtual Mini Bank! --------");
        System.out.println(" ");
        
        while (choice != 5)
        {
            System.out.println("Menu : ");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.println("Enter your choice : ");
            choice = cin.nextInt();
            
            switch (choice) {
                case 1 -> System.out.println("Open Acocound in pending...");  
                case 2 -> System.out.println("Deposit in pending...");
                case 3 -> System.out.println("Withdraw in pending...");
                case 4 -> System.out.println("Transfer in pending...");
                case 5 ->{
                    System.out.println("Closing the bank...");
                }
                default -> System.out.println("Enter correct choice");
            }
            System.out.println("---------------");

        }
        cin.close();
    }
}
