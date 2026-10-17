package clg.p7.p7_3;

import java.lang.reflect.Field;

public class ColumnMapping {
    public static void main(String[] args) throws Exception {
        String[] header = {"student_name" , "student_id"};
        String[] data = {"Zeel" , "74"};

        Student s = new Student();

        Field[] fields = s.getClass().getDeclaredFields();
        
        for(Field field : fields)
        {
            Column column = field.getAnnotation(Column.class);

            for (int i = 0; i < header.length; i++)
            {
                if (header[i].equals(column.name()))
                {
                    if (field.getType() == int.class)
                    {
                        field.set(s, Integer.parseInt(data[i]));
                    }else
                    {
                        field.set(s, data[i]);
                    }
                }
            }
        }
        s.displayStudent();
    }
}
