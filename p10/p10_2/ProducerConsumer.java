package clg.p10.p10_2;

public class ProducerConsumer 
{
    static int buffer;
    static boolean available = false;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized(ProducerConsumer.class) 
                {
                    try
                    {
                        while(available)
                            ProducerConsumer.class.wait();

                        buffer = i;
                        available = true;
                        System.out.println("Produced: " + i);

                        ProducerConsumer.class.notify();
                    } 
                    catch(InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized(ProducerConsumer.class)
                {
                    try
                    {
                        while(!available)
                            ProducerConsumer.class.wait();

                        System.out.println("Consumed: " + buffer);
                        available = false;

                        ProducerConsumer.class.notify();
                    }
                    catch(InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}