package clg.p10.p10_3;

public class DeadLock {
    static Object lockA = new Object();
    static Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException 
    {
        boolean preventDeadlock = false;

        Thread t1 = new Thread(() -> 
        {
            if(preventDeadlock) 
            {
                synchronized(lockA)
                {
                    synchronized(lockB) 
                    {
                        System.out.println("Thread 1 completed");
                    }
                }
            } 
            else
            {
                synchronized(lockA)
                {
                    System.out.println("Thread 1 locked A");
                    try
                    { 
                        Thread.sleep(100); 
                    }
                    catch(InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    synchronized(lockB) 
                    {
                        System.out.println("Thread 1 locked B");
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> 
        {
            if(preventDeadlock)
            {
                synchronized(lockA) 
                {
                    synchronized(lockB) 
                    {
                        System.out.println("Thread 2 completed");
                    }
                }
            }
            else 
            {
                    synchronized (lockB) {
                    System.out.println("Thread 2 locked B");
                    try 
                    { 
                        Thread.sleep(100); 
                    }
                    catch(InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    synchronized(lockA)
                    {
                        System.out.println("Thread 2 locked A");
                    }
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Main completed");
    }
}