package ru.netology.out;

import ru.netology.Product;
import ru.netology.orders.Order;
import ru.netology.orders.OrdersList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPrinter {

    public static void print(List<Product> products) {
        System.out.println("Доступные товары:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) +". " + products.get(i).toString());
        }
    }

    public static void printBasket(HashMap<Product, Integer> basket) {
        System.out.println("Ваша корзина:");
        if (!basket.isEmpty()) {
            basket.entrySet().stream()
                    .map(x -> x.getKey().toString() + ". Количество: " + x.getValue())
                    .forEach(System.out::println);
            int sum = basket.entrySet().stream()
                    .map(x -> x.getKey().getPrice() * x.getValue())
                    .reduce(0, Integer::sum);
            System.out.printf("Итого: %d руб. %n%n", sum);
        } else {
            System.out.println("Пусто");
        }
    }

    public static void printOrders(OrdersList orders) {
        System.out.println("Предыдущие заказы:");
        for (Order order : orders) {
            System.out.println("______________________________");
            System.out.printf("Заказ №%d%n", order.getOrderId());
            System.out.println("Список товаров:");
            for (Map.Entry<Product, Integer> content : order.getContent().entrySet()) {
                System.out.println(content.getKey().toString() + ", Количество: " + content.getValue());
            }
            System.out.println("Стоимость: " + order.getCost());
            System.out.println();
        }
    }
}
