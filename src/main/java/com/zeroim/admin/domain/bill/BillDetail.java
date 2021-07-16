package com.zeroim.admin.domain.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BillDetail")
public class BillDetail {
    @Id
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal value;
}
