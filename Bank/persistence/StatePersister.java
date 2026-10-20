package clg.Bank.persistence;

import clg.Bank.model.Account;
import java.io.*;
import java.nio.file.Path;

public class StatePersister
{
    public static void save(Account[] accounts, Path file)
    {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toFile())))
        {
            out.writeObject(accounts);
            System.out.println("Accounts saved successfully.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Account[] load(Path file)
    {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toFile())))
        {
            return (Account[]) in.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return new Account[0];
        }
    }
}