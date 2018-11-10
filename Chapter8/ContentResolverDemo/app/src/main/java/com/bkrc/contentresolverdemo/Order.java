package com.bkrc.contentresolverdemo;


public class Order {
    public int id;
    public String customName;
    public int orderPrice;
    public String country;
    public Order() {
    }

    public Order(int id, String customName, int orderPrice, String country) {
        this.id = id;
        this.customName = customName;
        this.orderPrice = orderPrice;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customName='" + customName + '\'' +
                ", orderPrice=" + orderPrice +
                ", country='" + country + '\'' +
                '}';
    }
}
