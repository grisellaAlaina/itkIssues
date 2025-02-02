package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ItkIssuesApplication {

        public static <T> Map<T, Integer> countElements(T[] array) {
            Map<T, Integer> elementCountMap = new HashMap<>();
            for (T element : array) {
                if (elementCountMap.containsKey(element)) {
                    elementCountMap.put(element, elementCountMap.get(element) + 1);
                } else {
                    elementCountMap.put(element, 1);
                }
            }
            return elementCountMap;
        }

        public static void main(String[] args) {
            SpringApplication.run(ItkIssuesApplication.class, args);

            String[] array = {"some", "element", "some", "strange", "element", "here"};
            Map<String, Integer> result = countElements(array);

            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            Integer[] intrr = {1, 2, 3, 1, 2, 3, 45};
            Map<Integer, Integer> result2 = countElements(intrr);

            for (Map.Entry<Integer, Integer> entry : result2.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }


        }
    }
