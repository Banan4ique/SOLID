package ru.netology;

import ru.netology.filters.KeywordFilter;
import ru.netology.filters.PriceFilter;
import ru.netology.filters.ProducerFilter;
import ru.netology.filters.ProductsFilter;
import ru.netology.orders.CanceledOrder;
import ru.netology.orders.Order;
import ru.netology.orders.OrdersList;
import ru.netology.out.ProductPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Product>  products = new ArrayList<>();
    private static final HashMap<Product, Integer> basket = new HashMap<>();
    private static final OrdersList ordersList = new OrdersList();
    private static final List<CanceledOrder> canceledOrders = new ArrayList<>();

    public static void main(String[] args) {
        products.add(new Product("Шоколад \"Алёнка\"", 150, "Красный Октябрь"));
        products.add(new Product("Пломбир", 100, "Чистая линия"));
        products.add(new Product("Бананы", 200, "Эквадор"));
        products.add(new Product("Мини бананы", 300, "Эквадор"));
        products.add(new Product("Огурцы", 200, "Краснодарский край"));
        products.add(new Product("Огурцы маринованные", 280, "Дядя Ваня"));

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Добро пожаловать в магазин!");
        while(true) {
            System.out.println("Доступные функции: ");
            System.out.println("1. Вывод доступных для покупки товаров");
            System.out.println("2. Фильтрация товаров");
            System.out.println("3. Добавление продуктов в корзину");
            System.out.println("4. Оформление заказа");
            System.out.println("5. Показ истории заказов");
            System.out.println("6. Возврат заказа, повторение заказа");
            System.out.print("Введите номер интересующей вас команды или введите \"end\" для выхода: ");
            input = scanner.nextLine();
            System.out.println();

            switch (input) {
                case "1":
                    ProductPrinter.print(products);
                    System.out.println();
                    break;
                case "2":
                    while(true) {
                        System.out.println("Выберите по какому параметру фильтровать товары:");
                        System.out.println("1. Ключевое слово");
                        System.out.println("2. Цена");
                        System.out.println("3. Производитель");
                        input = scanner.nextLine();
                        ProductsFilter filter;
                        switch (input) {
                            case "1":
                                System.out.print("Введите ключевое слово для поиска: ");
                                input = scanner.nextLine();
                                filter = new KeywordFilter();
                                filter.filter(products, input).forEach(System.out::println);
                                System.out.println();
                                break;
                            case "2":
                                System.out.print("Введите через пробел диапазон цен для поиска: ");
                                String[] prices = scanner.nextLine().split(" ");
                                int min = Integer.parseInt(prices[0]);
                                int max = Integer.parseInt(prices[1]);
                                filter = new PriceFilter();
                                filter.filter(products, List.of(min, max)).forEach(System.out::println);
                                System.out.println();
                                break;
                            case "3":
                                System.out.print("Введите производителя для поиска: ");
                                input = scanner.nextLine();
                                filter = new ProducerFilter();
                                filter.filter(products, input).forEach(System.out::println);
                                System.out.println();
                                break;
                            default:
                                System.out.println("Введен некорректный номер фильтра, повторите ввод");
                                continue;
                        }
                        break;
                    }
                    break;
                case "3":
                    ProductPrinter.print(products);
                    System.out.println("Выберете номер товара и его количество для добавления в корзину: ");
                    int productNumber = scanner.nextInt();
                    int productQuantity = scanner.nextInt();
                    basket.put(products.get(productNumber - 1), productQuantity);
                    ProductPrinter.printBasket(basket);
                    System.out.println();
                    break;
                case "4":
                    ProductPrinter.printBasket(basket);
                    System.out.println("Хотите завершить заказ? (Да/нет)");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("да")||input.equalsIgnoreCase("yes")) {
                        ordersList.add(new Order((HashMap<Product, Integer>) basket.clone()));
                        System.out.println("Заказ оформлен");
                        basket.clear();
                    }
                    break;
                case "5":
                    ProductPrinter.printOrders(ordersList);
                    break;
                case "6":
                    System.out.println("Выберите необходимое действие:");
                    System.out.println("1. Отменить заказ");
                    System.out.println("2. Повторить заказ");
                    input = scanner.nextLine();
                    if (input.equals("1")) {
                        ProductPrinter.printOrders(ordersList);
                        System.out.print("Введите номер заказа для отмены: ");
                        input = scanner.nextLine();
                        CanceledOrder canceled = new CanceledOrder(ordersList.refuse(Integer.parseInt(input)));
                        canceledOrders.add(canceled);
                        System.out.println("Заказ отменен");
                        System.out.println("Отмененные заказы:");
                        canceledOrders.forEach(System.out::println);
                    } else if (input.equals("2")) {
                        ProductPrinter.printOrders(ordersList);
                        System.out.print("Введите номер заказа для повторения: ");
                        input = scanner.nextLine();
                        ordersList.repeat(Integer.parseInt(input));
                        System.out.println("Новый заказ добавлен");
                    }
                    break;
                case "end":
                    return;
            }
        }
    }
}