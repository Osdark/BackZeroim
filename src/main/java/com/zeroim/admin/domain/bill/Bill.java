package com.zeroim.admin.domain.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Bill")
public class Bill {
    @Id
    private UUID id;
    private String billNumber;
    private Date date;
    private UUID clientId;
    private UUID[] billDetail;
    private BigDecimal total;
    private boolean paidOut;
}
