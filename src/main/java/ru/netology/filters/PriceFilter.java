package ru.netology.filters;

import ru.netology.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PriceFilter implements ProductsFilter{
    @Override
    public <T> List<Product> filter(List<Product> list, T minMax) {
        if (minMax instanceof List) {
            return list.stream()
                    .filter(x -> x.getPrice() >= ((List<Integer>) minMax).getFirst()
                            && x.getPrice() <= ((List<Integer>) minMax).get(1))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
