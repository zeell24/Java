package clg.p5.p5_3;

abstract class Media
{
    abstract double lateFee();
}

class Book extends Media
{
    double daysLate;

    Book(double daysLate)
    {
        this.daysLate = daysLate;
    }

    @Override
    double lateFee()
    {
        return 2 * daysLate;
    }
}

class DVD extends Media
{
    double daysLate;

    DVD(double daysLate)
    {
        this.daysLate = daysLate;
    }

    @Override
    double lateFee()
    {
        return 5 * daysLate;
    }
}

class Magazine extends Media
{
    double daysLate;

    Magazine(double daysLate)
    {
        this.daysLate = daysLate;
    }

    @Override
    double lateFee()
    {
        return 1 * daysLate;
    }
}

public class Main3 {
    public static void main(String[] args) {
        Media[] media = {
            new Book(3),
            new DVD(3),
            new Magazine(3)
        };

        double total = 0;

        for(Media m : media)
        {
            double fee = m.lateFee();
            System.out.println("Late fee : " + fee);

            total += fee;
        }

        System.out.println("Total fee : " + total);
    }
}
