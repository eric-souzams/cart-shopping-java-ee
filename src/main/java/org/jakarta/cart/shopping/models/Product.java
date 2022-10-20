package org.jakarta.cart.shopping.models;

import java.time.LocalDate;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private int price;
    private String sku;
    private LocalDate createdAt;

    public Product() {
    }

    public Product(Long id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.price = price;

        Category category = new Category();
        category.setName(type);
        this.category = category;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

