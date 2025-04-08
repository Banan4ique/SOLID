package ru.netology.filters;

import ru.netology.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProducerFilter implements ProductsFilter{
    @Override
    public <T> List<Product> filter(List<Product> list, T producer) {
        if (producer instanceof String) {
            return list.stream()
                    .filter(x -> x.getProducer().equalsIgnoreCase(((String) producer)))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
