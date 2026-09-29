package clg.p6.p6_2;

@FunctionalInterface
interface Notifier
{
    void send(String message);
}

interface Urgent{}

class UrgentNotifier implements Notifier, Urgent
{
    public void send(String message)
    {
        System.out.println("Urgent Notification : " + message);
    }
}

public class NotificationSender
{
    public static void main(String[] args)
    {
        Notifier email = message -> {   System.out.println("Email : " + message);   };

        Notifier sms = message ->
        {
            System.out.println("SMS : " + message);
        };

        UrgentNotifier urgent = new UrgentNotifier();

        Notifier[] senders = {email, sms, urgent};

        String message = "Meeting is scheduled at 5 PM.";

        System.out.println("Sending Notifications : \n");

        for(Notifier sender : senders)
        {
            sender.send(message);
            if(sender instanceof Urgent)
            {
                sender.send(message);
            }
        }
    }
}
