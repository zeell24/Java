package clg.p14.p14_2.src.com.example.model;

import java.io.Serializable;

public class Item implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;

    public Item(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}