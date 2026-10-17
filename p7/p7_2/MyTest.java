package clg.p7.p7_2;

public class MyTest {
    
    @Run
    void test1()
    {
        System.out.println("Test 1 executed");
    }

    @Run
    void test2()
    {
        System.out.println("Test 2 executed");
    }

    void function()
    {
        System.out.println("This method should not run");
    }
}
