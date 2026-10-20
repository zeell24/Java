package clg.p13.p13_3;

class GenericStack<T>
{
    private Object[] arr;
    private int top;

    GenericStack(int size)
    {
        arr = new Object[size];
        top = -1;
    }

    void push(T value)
    {
        if(top == arr.length - 1)
        {
            System.out.println("Stack is full");
            return;
        }

        arr[++top] = value;
    }

    @SuppressWarnings("unchecked")
    T pop()
    {
        if(top == -1)
        {
            System.out.println("Stack is empty");
            return null;
        }

        return (T) arr[top--];
    }
}

public class GenericStackDemo
{
    public static void main(String[] args)
    {
        GenericStack<Integer> s1 = new GenericStack<>(3);
        s1.push(10);
        s1.push(20);
        System.out.println(s1.pop());

        GenericStack<String> s2 = new GenericStack<>(3);
        s2.push("Java");
        s2.push("Generics");
        System.out.println(s2.pop());
    }
}