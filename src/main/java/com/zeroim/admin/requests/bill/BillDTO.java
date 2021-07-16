package com.zeroim.admin.requests.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private UUID id;
    private String billNumber;
    @NotBlank(message = "Date must not be null")
    private Date date;
    @NotBlank(message = "Client id must not be null")
    private UUID clientId;
    private List<UUID> billDetail;
    @NotBlank(message = "Total must not be null")
    @Positive
    private BigDecimal total;
    private boolean paidOut;
}
