package ru.practice.alcour.payment.model;

public class OrderDTO {
    private String orderId;
    private String name;
    private String status;

    public OrderDTO() {}

    public OrderDTO(String orderId, String name, String status) {
        this.orderId = orderId;
        this.name = name;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
