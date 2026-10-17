package clg.p7.p7_1;

import java.lang.reflect.Field;
import java.util.ArrayList;
// import java.util.List;

public class Validator {

    static ArrayList<String> validate(Object obj) 
    {
        ArrayList<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) 
        {
            try {
                field.setAccessible(true);

                Object value = field.get(obj);

                if(field.isAnnotationPresent(NotBlank.class))
                {
                    if(value == null || value.toString().isBlank())
                    {
                        errors.add(field.getName() + " must not be blank");
                    }
                }
            } 
            catch(Exception e)
            {
                errors.add("Error: " + field.getName());
            }
        }

        return errors;
    }

    public static void main(String[] args) {

        SignupForm form = new SignupForm("zeel", "xyz1234", "abc@gmail.com");

        ArrayList<String> errors = validate(form);

        for(String error : errors)
        {
            System.out.println(error);
        }
    }
}