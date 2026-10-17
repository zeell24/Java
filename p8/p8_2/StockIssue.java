package clg.p8.p8_2;

public class StockIssue 
{
    public static void main(String[] args) 
    {
        Warehouse w = new Warehouse();

        w.addStock("Laptop", 10);
        w.addStock("Mouse", 5);

        int[][] requests = {
            {3, 0},   
            {12, 0},  
            {-2, 0},  
            {2, 1},   
            {0, 1}    
        };

        String[] items = {"Laptop", "Mouse"};

        for (int[] request : requests) {
            int qty = request[0];
            String item = items[request[1]];

            try
            {
                w.issue(item, qty);
            }
            catch (OutOfStockException e) 
            {
                System.out.println(e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());
            }
            catch (InvalidQuantityException e)
            {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("All requests processed.");
    }
}
