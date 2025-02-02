package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;


@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);

        ForkJoinPool pool = new ForkJoinPool();
        FactorialTask task = new FactorialTask(5);
        System.out.println(pool.invoke(task));
    }
}