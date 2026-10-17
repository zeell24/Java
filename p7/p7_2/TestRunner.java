package clg.p7.p7_2;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        MyTest x = new MyTest();
   
        Method[] methods = x.getClass().getDeclaredMethods();

        int count = 0;
        for(Method method : methods)
        {
            if(method.isAnnotationPresent(Run.class))
            {
                method.invoke(x);
                count++;
            }
        }

        System.out.println("Methods run : " + count);
    }
}
