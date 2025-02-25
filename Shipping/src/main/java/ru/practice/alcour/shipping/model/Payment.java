package ru.practice.alcour.shipping.model;

public class Payment {
    private String orderId;
    private String status;
    private double amount;

    public Payment(String orderId, String status, double amount) {
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    public Payment() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}