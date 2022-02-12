package com.geekbrains.spring.web.api.stat;

public class ProductInCartsDto {
    private String name;

    public ProductInCartsDto() {
    }

    public ProductInCartsDto(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private Integer quantity;
}
