package com.zeroim.admin.ports.primary.buyer;

import com.zeroim.admin.requests.buyer.BuyerDTO;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/buyer")
public interface BuyerController {
    @PostMapping
    ResponseEntity<Response<BuyerDTO>> create(@RequestBody RequestCreateBuyerDTO buyerDTO);

    @GetMapping("/getById")
    ResponseEntity<Response<BuyerDTO>> getById(@RequestParam("id") UUID id);

    @GetMapping
    ResponseEntity<Response<List<BuyerDTO>>> getAll();

    @PutMapping("/update")
    ResponseEntity<Response<Boolean>> updateBuyer(@RequestBody RequestCreateBuyerDTO buyerDTO);
}
