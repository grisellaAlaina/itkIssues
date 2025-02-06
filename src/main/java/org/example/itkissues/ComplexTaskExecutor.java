package org.example.itkissues;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks have been completed. Merging results...");
        });
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i + 1;
            executorService.submit(() -> {
                ComplexTask task = new ComplexTask(taskId);
                task.execute();

                try {
                    barrier.await();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
    }
}