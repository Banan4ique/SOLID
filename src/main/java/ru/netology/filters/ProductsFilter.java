package ru.netology.filters;

import ru.netology.Product;

import java.util.List;

public interface ProductsFilter {
    <T> List<Product> filter(List<Product> list, T property);
}
