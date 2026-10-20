package clg.p12.p12_2;

import java.util.*;

public class UniqueVisitor
{
    public static void main(String[] args)
    {
        List<String> logins = Arrays.asList(
            "Zeel", "Tini", "Aditi", "Zeel", "Sweta", "swara"
        );

        HashSet<String> unique = new HashSet<>(logins);
        LinkedHashSet<String> ordered = new LinkedHashSet<>(logins);

        System.out.println("Unique visitors: " + unique.size());

        int count = 0;
        System.out.println("First 3 visitor : ");
        for(String name : ordered)
        {
            if(count == 3)
                break;

            System.out.println(name);
            count++;
        }
    }
}
