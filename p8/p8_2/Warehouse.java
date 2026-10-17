package clg.p8.p8_2;
import java.util.HashMap;

public class Warehouse {
    private HashMap<String, Integer> stock = new HashMap<>();

    public void addStock(String item, int qty) {
        stock.put(item, qty);
    }

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {

        if(qty <= 0) 
        {
            throw new InvalidQuantityException(
                "Quantity must be greater than zero."
            );
        }

        int available = stock.getOrDefault(item, 0);

        if(qty > available)
        {
            int shortfall = qty - available;

            throw new OutOfStockException(
                "Not enough stock for " + item,
                shortfall
            );
        }

        stock.put(item, available - qty);

        System.out.println("Issued " + qty + " " + item + ". Remaining stock: " + (available - qty));
    }
}