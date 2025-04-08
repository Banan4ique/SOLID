package ru.netology.filters;

import ru.netology.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordFilter implements ProductsFilter{
    @Override
    public <T> List<Product> filter(List<Product> list, T keyword) {
        if (keyword instanceof String) {
            return list.stream()
                    .filter(x -> x.getName().toLowerCase()
                            .contains((CharSequence) ((String) keyword).toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
