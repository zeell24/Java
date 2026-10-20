package clg.Bank.util;

import clg.Bank.model.annotation.Positive;
import clg.Bank.model.annotation.MaxLength;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationValidator
{
    public static String[] validate(Object obj)
    {
        ArrayList<String> errors = new ArrayList<>();

        Class<?> current = obj.getClass();

        while(current != null)
        {
            Field[] fields = current.getDeclaredFields();

            for(Field field : fields)
            {
                field.setAccessible(true);

                try
                {
                    Object value = field.get(obj);

                    if(field.isAnnotationPresent(Positive.class))
                    {
                        Positive p = field.getAnnotation(Positive.class);

                        if(value instanceof Number && ((Number)value).longValue() <= 0)
                        {
                            errors.add(field.getName() + " " + p.message());
                        }
                    }

                    if(field.isAnnotationPresent(MaxLength.class))
                    {
                        MaxLength m = field.getAnnotation(MaxLength.class);

                        if(value instanceof String && ((String)value).length() > m.value())
                        {
                            errors.add(field.getName() + " must have at most " + m.value() + " characters");
                        }
                    }
                }
                catch(IllegalAccessException e)
                {
                    errors.add("Could not access field: " + field.getName());
                }
            }

            current = current.getSuperclass();
        }
        
        return errors.toArray(new String[0]);
    }
}