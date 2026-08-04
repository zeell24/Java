// to run in terminal
//     > javac Fraction.java Driver.java
//     > java Driver.java

package clg.p3.p3_3;

public class Driver {
    public static void main(String[] args) {
        
        Fraction f[] = {
            new Fraction(4, 6),
            new Fraction(2, 3),
            new Fraction(8, 12)
        };

        for(int i=0 ; i < f.length ; i++)
        {
            System.out.println(f[i]);
        }

        System.out.println("f[0] == f[1] :" + f[0].equals(f[1]));

    }
}
