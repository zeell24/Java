package clg.p7.p7_1;

public class SignupForm 
{
    @NotBlank 
    String usename;

    @NotBlank 
    @MaxLength(20)
    String password;

    @NotBlank
    @MaxLength(50)
    String email;

    public SignupForm(String username , String password , String email)
    {
        this.usename = username;
        this.password = password;
        this.email = email;
    }
}
