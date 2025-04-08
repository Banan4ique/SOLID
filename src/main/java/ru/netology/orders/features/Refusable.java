package ru.netology.orders.features;
import ru.netology.orders.Order;

public interface Refusable {
    Order refuse(Integer id);
}
