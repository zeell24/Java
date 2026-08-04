package clg.p2;
import java.util.Scanner;

public class Thermostat
{
    private String location;
    private int temperature;

    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeAccount = 0;

    Thermostat(String location , int temperature)
    {
        this.location = location;
        this.temperature = temperature;

        activeAccount++;
    }

    Thermostat(String location)
    {
        this(location, 22);
    }

    void raise()
    {
        if(temperature < MAX){
            temperature++;
            System.out.println("Temperature of " + location + " : " + temperature);
        }
        else
            System.out.println("Already at maximum (30)");
    }

    void lower()
    {
        if(temperature > MIN){
            temperature--;
            System.out.println("Temperature of " + location + " : " + temperature);
        }
        else
            System.out.println("Already at minimum (16)");
    }
    
    int getTemperature()
    {
        return temperature;
    }

    static int getActiveCount()
    {
        return activeAccount;
    }

    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        Thermostat T1 = new Thermostat("home", 22);
        Thermostat T2 = new Thermostat("library");

        for(int i=0 ; i<10 ; i++)
        {
            T1.raise();
        }

        System.out.println(" ");
        for(int i=0 ; i<20 ; i++)
        {
            T2.lower();
        }

        System.out.println(" ");
        System.out.println("Total Active Count : " + getActiveCount());
        cin.close();
    }
}