package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);


        List<Order> orders = Arrays.asList(
                new Order("ProductA", 100),
                new Order("ProductB", 200),
                new Order("ProductA", 150),
                new Order("ProductC", 300),
                new Order("ProductD", 2)
        );

        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                        Collectors.summingDouble(Order::getCost)));

        List<Map.Entry<String, Double>> sortedProducts = totalCostByProduct.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 most expensive products:");
        for (Map.Entry<String, Double> entry : sortedProducts) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}