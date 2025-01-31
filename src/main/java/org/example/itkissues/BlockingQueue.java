package org.example.itkissues;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue<T> {
    private final List<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        if (queue.size() == 0) {
            notifyAll();
        }
        queue.add(item);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        if (queue.size() == capacity) {
            notifyAll();
        }
        return queue.remove(0);
    }

    public synchronized int size() {
        return queue.size();
    }
}
