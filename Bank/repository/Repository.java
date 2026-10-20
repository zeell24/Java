
package clg.Bank.repository;

import clg.Bank.model.annotation.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.ArrayList;

public class Repository<T>
{
    private HashMap<String, T> store = new HashMap<>();

    public T save(T entity)
    {
        try
        {
            Class<?> type = entity.getClass();

            while(type != null)
            {
                for(Field field : type.getDeclaredFields())
                {
                    if(field.isAnnotationPresent(Id.class))
                    {
                        field.setAccessible(true);
                        String id = (String) field.get(entity);
                        store.put(id, entity);
                        return entity;
                    }
                }

                type = type.getSuperclass();
            }

            throw new IllegalArgumentException("No @Id field found.");
        }
        catch(IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public T findById(String id)
    {
        return store.get(id);
    }

    public ArrayList<T> findAll()
    {
        return new ArrayList<>(store.values());
    }

    public boolean delete(String id)
    {
        return store.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items)
    {
        for(T item : items)
        {
            save(item);
        }
    }

    public static <N extends Number> double average(N[] values)
    {
        if(values.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        double sum = 0;

        for(N value : values)
        {
            sum += value.doubleValue();
        }

        return sum / values.length;
    }
}
