package clg.p8.p8_2;

public class OutOfStockException extends Exception
{
    private int shortfall;

    public OutOfStockException(String msg, int shortfall) {
        super(msg);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}
