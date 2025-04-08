package ru.netology.orders;
import ru.netology.Product;

import java.util.HashMap;

public class Order {

    protected Integer orderId;
    protected HashMap<Product, Integer> content;
    protected Integer cost;

    public Order(HashMap<Product, Integer> content) {
        orderId = OrdersList.getCounter();
        this.content = content;
        cost = content.entrySet().stream()
                .map(x -> x.getKey().getPrice() * x.getValue())
                .reduce(0, Integer::sum);
    }

    public Order(Order order) {
        orderId = OrdersList.getCounter();
        this.content = order.content;
        this.cost = order.cost;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public HashMap<Product, Integer> getContent() {
        return content;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Номер заказа: " + orderId +
                ", товары: " + content +
                ", стоимость: " + cost;
    }
}
