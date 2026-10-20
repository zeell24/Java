package clg.p10.p10_1;
import java.util.concurrent.*;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService ex = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int id = i;

            ex.submit(() -> {
                System.out.println("Task " + id + " running on "
                        + Thread.currentThread().getName());

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All tasks completed.");
    }
}
