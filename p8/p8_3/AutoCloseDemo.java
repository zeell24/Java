package clg.p8.p8_3;

class MyResource implements AutoCloseable {
    public void use() {
        System.out.println("Resource is being used.");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource closed.");
        throw new Exception("Error while closing resource");
    }
}

public class AutoCloseDemo {
    public static void main(String[] args) 
    {
        try(MyResource r = new MyResource()) 
        {
            r.use();
            throw new Exception("Original error inside try block");
        }
        catch(Exception e)
        {
            System.out.println("Caught: " + e.getMessage());

            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}