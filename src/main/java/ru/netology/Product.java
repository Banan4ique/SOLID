package ru.netology;

import static java.util.Objects.hash;

public class Product{
    protected String name;
    protected Integer price;
    protected String producer;

    public Product(String name, int price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public String toString() {
        return String.format("Товар: %s, цена: %d руб., производитель: %s", name, price, producer);
    }

    @Override
    public int hashCode() {
        return hash(name, price, producer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        else {
            Product product = (Product) obj;
            return this.name.equals(product.name)
                    && this.price == product.price
                    && this.producer.equals(product.producer);
        }
    }
}
