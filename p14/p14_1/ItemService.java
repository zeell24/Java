package clg.p14.p14_1;
import java.util.ArrayList;

public class ItemService
{
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item)
    {
        items.add(item);
    }

    public int getCount()
    {
        return items.size();
    }

    public void performAction(String action)
    {
        if(!action.equals("add"))
        {
            throw new IllegalArgumentException("Invalid action");
        }
    }
}