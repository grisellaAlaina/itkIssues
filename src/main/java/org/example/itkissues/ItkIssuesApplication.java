package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);

        Integer[] arr = {1, 2, 3, 4, 7, 8};
        FilterService filterService = new FilterService();
        EvenFilter evenFilter = new EvenFilter();

        List<Integer> filtredArr = new ArrayList<>(filterService.filterMethod(arr, evenFilter));

        for (var i : filtredArr) {
            System.out.println(i);
        }
    }
}
