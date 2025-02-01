package org.example.itkissues;

public class Order {
    private final String product;
    private final int cost;

    public Order(String product, int cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public int getCost() {
        return cost;
    }
}
