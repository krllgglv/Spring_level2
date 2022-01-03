package com.geekbrains.spring.web.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_per_product")
    private Integer pricePerProduct;

    @Column(name = "price")
    private Integer price;
}
