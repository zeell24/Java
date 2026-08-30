package clg.p4.p4_1;

public class PasswordChecker {

    public boolean checkLength(String pw)
    {
        if(pw.matches(".*{8,}.*"))
            return true;
        else 
            return false;
    }

    public boolean checkUpperCase(String pw)
    {
        if(pw.matches(".*[A-Z].*"))
            return true;
        else
            return false;
    }

    public boolean checkDigit(String pw)
    {
        if(pw.matches(".*[0-9].*"))
            return true;
        else
            return false;
    }

    public boolean checkSpecialChar(String pw)
    {
        if(pw.matches(".*[^A-Za-z0-9].*"))
            return true;
        else 
            return false;
    }
}
