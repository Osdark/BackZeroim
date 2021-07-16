package com.zeroim.admin.requests.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillDTO {
    NewBillDTO billDTO;
    List<BillDetailDTO> billDetailDTOS;
}
