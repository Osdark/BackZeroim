package com.zeroim.admin.ports.primary.product;

import com.zeroim.admin.requests.product.ProductCategoryDTO;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/productCategory")
public interface ProductCategoryController {
    @PostMapping("/create")
    ResponseEntity<Response<ProductCategoryDTO>> create(@RequestParam("category") String category);

    @GetMapping
    ResponseEntity<Response<List<ProductCategoryDTO>>> getAll();
}
