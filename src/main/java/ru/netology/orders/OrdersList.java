package ru.netology.orders;

import ru.netology.orders.features.Refusable;
import ru.netology.orders.features.Repeatable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrdersList implements Iterable<Order>, Refusable, Repeatable {

    protected static Integer counter = 1;
    protected List<Order> list;

    public OrdersList() {
        list = new ArrayList<>();
    }

    public static Integer getCounter() {
        return counter;
    }

    public void add(Order order) {
        list.add(order);
        counter++;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<>() {

            int next = 0;

            @Override
            public boolean hasNext() {
                return next < list.size();
            }

            @Override
            public Order next() {
                if (hasNext()) {
                    return list.get(next++);
                } else {
                    return list.getFirst();
                }
            }
        };
    }

    @Override
    public Order refuse(Integer id) {
        for (Order order : list) {
            if (order.getOrderId().equals(id)) {
                list.remove(order);
                return order;
            }
        }
        return null;
    }

    @Override
    public void repeat(Integer id) {
        for (Order order : list) {
            if (order.getOrderId().equals(id)) {
                add(new Order(order));
                break;
            }
        }
    }
}
