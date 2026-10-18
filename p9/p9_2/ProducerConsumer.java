package clg.p9.p9_2;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    static Queue<Integer> buffer = new LinkedList<>();
    static final int SIZE = 3;
    static final int ITEMS = 10;

    static class Producer implements Runnable {
        public void run() {
            try {
                for (int i = 1; i <= ITEMS; i++) {
                    synchronized (buffer) {
                        while (buffer.size() == SIZE) {
                            buffer.wait();
                        }

                        buffer.add(i);
                        System.out.println("Produced: " + i);
                        buffer.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            try {
                for (int i = 1; i <= ITEMS; i++) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) {
                            buffer.wait();
                        }

                        int item = buffer.remove();
                        System.out.println("Consumed: " + item);
                        buffer.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items processed.");
    }
}

