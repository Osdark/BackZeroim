package com.zeroim.admin.requests.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDTO {
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal value;
}
