package org.example.inventory.service;

import org.example.inventory.dtos.request.SupplierDTO;
import org.example.inventory.dtos.respon.SupplierResponse;
import org.example.inventory.models.Supplier;

import java.util.List;

public interface SupplierService {
    SupplierResponse createSupplier(SupplierDTO supplier);
    List<SupplierResponse> getSuppliersAll();
    SupplierResponse getSupplierById(Long id);
    SupplierResponse updateSupplier(SupplierDTO supplierDTO,Long id);
    void deleteSupplier(Long id);

}
