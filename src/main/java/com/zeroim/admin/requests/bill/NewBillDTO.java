package com.zeroim.admin.requests.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBillDTO {
    @NotBlank(message = "Bill number must not be null")
    private String billNumber;
    @NotBlank(message = "Date must not be null")
    private Date date;
    @NotBlank(message = "Client id must not be null")
    private UUID clientId;
    @NotBlank(message = "Total must not be null")
    @Positive
    private BigDecimal total;
}
