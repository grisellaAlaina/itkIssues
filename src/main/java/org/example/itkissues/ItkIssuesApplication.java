package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);

        List<Order> orders = Stream.generate(() -> new Order(
                "AliExpressProduct" + (char)('a' + new Random().nextInt()),
                new Random().nextInt(1500)
        ))
                .limit(5)
                .flatMap(order -> {
                    if(order.getCost() < 750) {
                        return Stream.of(order, new Order(order.getProduct(), order.getCost()));
                    } else {
                        return Stream.of(order);
                    }
                })
                .toList();


        System.out.println("Product list:");
        orders.forEach(order -> System.out.println(order.getProduct() + ": " + order.getCost()));

        List<Map.Entry<String, Integer>> topProducts = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingInt(Order::getCost)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .toList();

        System.out.println("------top list:");
        topProducts.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));



    }

    }
