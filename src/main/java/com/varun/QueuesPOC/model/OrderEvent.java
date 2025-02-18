package com.varun.QueuesPOC.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent  {
    private String orderId;
    private String customerId;
    private double amount;
    private String status;
}