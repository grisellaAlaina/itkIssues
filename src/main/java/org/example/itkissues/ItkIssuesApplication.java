package org.example.itkissues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ItkIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkIssuesApplication.class, args);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);

        DateTimeModel dateTimeModel = new DateTimeModel(localDateTime);
        System.out.println("format localDateTime: " + dateTimeModel.getFormattedDateTime());
    }

}
