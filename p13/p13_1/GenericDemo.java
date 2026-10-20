package clg.p13.p13_1;

class Box<T>
{
    private T value;

    Box(T value)
    {
        this.value = value;
    }

    T get()
    {
        return value;
    }

    void set(T value)
    {
        this.value = value;
    }
}

class Pair<A, B>
{
    A first;
    B second;

    Pair(A first, B second)
    {
        this.first = first;
        this.second = second;
    }
}

public class GenericDemo
{
    static <T> T first(T[] arr)
    {
        return arr[0];
    }

    public static void main(String[] args)
    {
        Box<Integer> b1 = new Box<>(10);
        Box<String> b2 = new Box<>("Java");

        System.out.println(b1.get());
        System.out.println(b2.get());

        b1.set(20);
        System.out.println(b1.get());

        Pair<String, Integer> p = new Pair<>("Age", 19);
        System.out.println(p.first + ": " + p.second);

        String[] names = {"Zeel", "Priya", "Tini"};
        System.out.println(first(names));
    }
}