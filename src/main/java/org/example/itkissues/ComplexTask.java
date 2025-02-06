package org.example.itkissues;

import java.util.Random;

public class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " is executing task " + taskId);

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " completed task " + taskId);
    }
}
