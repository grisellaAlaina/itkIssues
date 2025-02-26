package ru.practice.alcour.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practice.alcour.orders.producer.WebLogProducer;

@RestController
@RequestMapping("/api/weblog")
public class WebLogController {

    private final WebLogProducer webLogProducer;

    @Autowired
    public WebLogController(WebLogProducer webLogProducer) {
        this.webLogProducer = webLogProducer;
    }

    @PostMapping
    public String sendWebLog(@RequestParam Integer message) {
        for(int i = 0; i < message; i++) {
            String topicMessage = "message number " + i;

            webLogProducer.sendMessage(topicMessage, topicMessage);
        }
        return "Сообщений отправлено в Kafka тему: " + message;
    }
}
