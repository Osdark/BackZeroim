package com.zeroim.admin.domain.buyer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Buyer")
public class Buyer {
    @Id
    private UUID id;
    private String idNumber;
    private UUID idType;
    private String name;
    private String address;
    private String phone;
}
