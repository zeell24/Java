package clg.p6.p6_1;

interface Switchable
{
    void on();
    void off();
    boolean isOn();

    default void toggle()
    {
        if(isOn())
            off();
        else
            on();
    }
}

class Fan implements Switchable
{
    private boolean status = false;

    public void on()
    {
        status = true;
        System.out.println("Fan : ON");
    }

    public void off()
    {
        status = false;
        System.out.println("Fan : OFF");
    }

    public boolean isOn() { return status; }
}

class Light implements Switchable
{
    private boolean status = false;

    public void on()
    {
        status = true;
        System.out.println("Light : ON");
    }

    public void off()
    {
        status = false;
        System.out.println("Light : OFF");
    }

    public boolean isOn() { return status; }
}

@FunctionalInterface
interface SwitchRule
{
    boolean maySwitchOn(Switchable device , int hour); 
}

public class RemoteControl
{
    public static void main(String[] args)
    {
        Fan fan = new Fan();
        Light light = new Light();

        Switchable[] devices = {fan , light};

        System.out.println("Toggling Devices : ");

        for(Switchable device : devices)
        {
            device.toggle();
        }

        SwitchRule rule = new SwitchRule() {

            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        System.out.println("\nAnonymous Class : ");

        System.out.println(
            "Can the Switch of fan be ON at 10 AM?" + " " 
            + rule.maySwitchOn(fan, 10)
        );

        SwitchRule lambdaRule = (device , hour) -> hour >= 6 && hour <= 22;

        System.out.println("\nLambda Implementation : ");

        System.out.println(
            "Can the Switch of light be ON at 11 PM?" + " " 
            + lambdaRule.maySwitchOn(light, 23)
        );
    }
}