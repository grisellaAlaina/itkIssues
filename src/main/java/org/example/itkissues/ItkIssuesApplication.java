package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);

        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.enqueue(i);
                    System.out.println("produce: " + i + " (queue size: " + queue.size() + ")");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = queue.dequeue();
                    System.out.println("consume: " + item + " (queue size: " + queue.size() + ")");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        executor.submit(producer);
        executor.submit(consumer);

        executor.shutdown();
    }
}