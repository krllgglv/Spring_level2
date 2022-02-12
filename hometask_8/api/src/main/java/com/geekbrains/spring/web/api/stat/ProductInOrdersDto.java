package com.geekbrains.spring.web.api.stat;

public class ProductInOrdersDto {
    private String name;

    public ProductInOrdersDto() {
    }

    public ProductInOrdersDto(String name, Integer quantity) {
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
