package com.zeroim.admin.ports.primary.bill;

import com.zeroim.admin.requests.bill.BillDetailDTO;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("/admin/billDetail")
public interface BillDetailController {
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getById")
    ResponseEntity<Response<BillDetailDTO>> getById(@RequestParam("id") UUID id);
}
