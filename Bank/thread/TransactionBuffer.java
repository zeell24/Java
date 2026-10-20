
package clg.Bank.thread;

public class TransactionBuffer
{
    private int[] buffer = new int[3];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void produce(int value) throws InterruptedException
    {
        while(count == buffer.length)
            wait();

        buffer[in] = value;
        in = (in + 1) % buffer.length;
        count++;

        System.out.println("Produced transaction: " + value);
        notify();
    }

    public synchronized int consume() throws InterruptedException
    {
        while(count == 0)
            wait();

        int value = buffer[out];
        out = (out + 1) % buffer.length;
        count--;

        notify();
        return value;
    }
}
