package clg.p14.p14_2.src.com.example.service;
import clg.p14.p14_2.src.com.example.model.Item;
import java.util.ArrayList;
import java.io.*;

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

    public void save(String filename) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(filename));
        out.writeObject(items);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load(String filename) throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream(filename));
        items = (ArrayList<Item>) in.readObject();
        in.close();
    }
}