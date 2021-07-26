package com.zeroim.admin.ports.primary.product;

import com.zeroim.admin.requests.product.ProductDTO;
import com.zeroim.admin.requests.product.RequestUpdateProductDTO;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/product")
public interface ProductController {
    @PostMapping
    ResponseEntity<Response<ProductDTO>> create(@RequestBody ProductDTO productDTO);

    @GetMapping("/getById")
    ResponseEntity<Response<ProductDTO>> getById(@RequestParam("id")UUID id);

    @GetMapping
    ResponseEntity<Response<List<ProductDTO>>> getAll();

    @GetMapping("/getByCategory")
    ResponseEntity<Response<List<ProductDTO>>> getByCategory(@RequestParam("categoryId") UUID categoryId);

    @PutMapping("/update")
    ResponseEntity<Response<ProductDTO>> update(@RequestBody RequestUpdateProductDTO productDTO);
}
