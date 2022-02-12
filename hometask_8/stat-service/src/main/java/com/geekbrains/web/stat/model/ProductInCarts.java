package com.geekbrains.web.stat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products_in_carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCarts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   @Column(name = "product_id")
    private Long productId;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "day_of_year")
    private Integer dayOfYear;

    @Column(name = "year")
    private Integer year;

}
