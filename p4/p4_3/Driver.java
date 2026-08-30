// > javac TemplateFiller.java Driver.java
// > java Driver.java

package clg.p4.p4_3;

public class Driver {
    public static void main(String[] args) {
        String template =
            "Dear {name}, order {id} ships {date}.";

        String[] names = {"name", "id"};
        String[] values = {"Zeel", "A24"};

        String result = TemplateFiller.fillTemplate(template, names, values);

        System.out.println(result);
    }
}
