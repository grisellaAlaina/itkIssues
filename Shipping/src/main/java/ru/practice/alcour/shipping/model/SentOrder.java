package ru.practice.alcour.shipping.model;



public class SentOrder {
    private String orderId;
    private String status;
    private String shipmentId;
    private String trackingNumber;

    public SentOrder() {
    }

    public SentOrder(String orderId, String status, String shipmentId, String trackingNumber) {
        this.orderId = orderId;
        this.status = status;
        this.shipmentId = shipmentId;
        this.trackingNumber = trackingNumber;
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

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}

