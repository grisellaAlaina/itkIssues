package org.example.itkissues;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DateTimeModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd##HH:mm:ss:SSS", locale = "en")
    private LocalDateTime dateTime;

    public DateTimeModel(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getFormattedDateTime() {
        return dateTime.format(
                new java.time.format.DateTimeFormatterBuilder()
                        .appendPattern("yyyy:MM:dd'##'HH:mm:ss:SSS")
                        .toFormatter(java.util.Locale.ENGLISH)
        );
    }
}
