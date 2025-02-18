package com.varun.QueuesPOC.controller;


import com.varun.QueuesPOC.model.OrderEvent;
import com.varun.QueuesPOC.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService producerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }


    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        try {
            producerService.sendOrder(orderEvent); // Key is orderId
            return ResponseEntity.ok("Order event sent successfully!"); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending order event: " + e.getMessage()); // 500 Internal Server Error
        }
    }

}