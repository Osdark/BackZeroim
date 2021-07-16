package com.zeroim.admin.requests.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResError {
    private int errorCode;
    private String message;
}
