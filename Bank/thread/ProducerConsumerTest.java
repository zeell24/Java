package clg.Bank.thread;

public class ProducerConsumerTest
{
    public static void main(String[] args)
    {
        TransactionBuffer buffer = new TransactionBuffer();

        Thread producer = new Thread(() ->
        {
            try
            {
                for(int i = 1; i <= 5; i++)
                    buffer.produce(i * 500);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() ->
        {
            try
            {
                for(int i = 1; i <= 5; i++)
                {
                    int amount = buffer.consume();
                    System.out.println("Processed transaction: " + amount);
                }
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
