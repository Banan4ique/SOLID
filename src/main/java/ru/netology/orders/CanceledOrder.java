package ru.netology.orders;

import ru.netology.Product;

import java.time.LocalDateTime;
import java.util.HashMap;

import static java.time.LocalDateTime.now;

public class CanceledOrder extends Order{

    protected LocalDateTime cancelDate;

    public CanceledOrder(HashMap<Product, Integer> content) {
        super(content);
    }

    public CanceledOrder(Order order) {
        this.orderId = order.orderId;
        this.content = order.content;
        this.cost = order.cost;
        cancelDate = now();
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    @Override
    public String toString() {
        return "Номер заказа: " + orderId +
                ", товары: " + content +
                ", стоимость: " + cost +
                ", время отмены: " + cancelDate;
    }
}
