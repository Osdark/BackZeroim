package com.zeroim.admin.requests.buyer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDTO {
    private UUID id;
    private String idNumber;
    private UUID idType;
    private String name;
    private String address;
    private String phone;
}
