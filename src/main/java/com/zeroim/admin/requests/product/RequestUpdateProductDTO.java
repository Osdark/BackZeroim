package com.zeroim.admin.requests.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateProductDTO {
    private UUID id;
    private String code;
    private String name;
    private int weight;
    private int units;
    private BigDecimal price;
    private UUID category;
}
