package com.zeroim.admin.ports.primary.buyer;

import com.zeroim.admin.requests.buyer.IdTypeDTO;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/idType")
public interface IdTypeController {
    @PostMapping
    ResponseEntity<Response<IdTypeDTO>> create(@RequestBody IdTypeDTO idTypeDTO);

    @GetMapping
    ResponseEntity<Response<List<IdTypeDTO>>> getAll();

    @GetMapping("/getById")
    ResponseEntity<Response<IdTypeDTO>> getById(@RequestParam("id") UUID id);

    @DeleteMapping
    ResponseEntity<Response<Integer>> delete(UUID id);
}
