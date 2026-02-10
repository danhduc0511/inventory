package org.example.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.SupplierDTO;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.dtos.respon.SupplierResponse;
import org.example.inventory.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') ")
    public DataResponse<SupplierResponse> createSupplier(@RequestBody SupplierDTO supplier) {
        SupplierResponse supplierResponse = supplierService.createSupplier(supplier);
        return DataResponse.<SupplierResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Supplier created successfully")
                .data(supplierResponse)
                .build();
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DataResponse<List<SupplierResponse>> allSuppliers() {
        List<SupplierResponse> suppliers = supplierService.getSuppliersAll();
        return DataResponse.<List<SupplierResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all suppliers successfully")
                .data(suppliers)
                .build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DataResponse<SupplierResponse> getSupplierById(@PathVariable Long id){
        SupplierResponse supplierResponse= supplierService.getSupplierById(id);
        return DataResponse.<SupplierResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get supplier by id successfully")
                .data(supplierResponse)
                .build();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public DataResponse<SupplierResponse> updateSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable Long id) {
        SupplierResponse supplierResponse = supplierService.updateSupplier(supplierDTO, id);
        return DataResponse.<SupplierResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Update supplier successfully")
                .data(supplierResponse)
                .build();
    }
}
