package clg.Bank;

public class Customer {
    
    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter = 101;
    
    Customer(String name , String email , String mobile)
    {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        customerId = generateCustomerId();
    }

    private static String generateCustomerId()
    {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public String getName()
    { 
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }

    public String getMobile()
    {
        return mobile;
    }

    public String getCustomerID()
    {
        return customerId;
    }


}
