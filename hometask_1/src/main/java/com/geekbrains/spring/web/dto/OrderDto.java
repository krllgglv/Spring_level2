package com.geekbrains.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private Integer totalPrice;
    private String address;
    private String phone;
}
