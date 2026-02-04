package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.SupplierDTO;
import org.example.inventory.dtos.respon.SupplierResponse;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.mapper.SupplierMapper;
import org.example.inventory.models.Supplier;
import org.example.inventory.repository.SupplierReponsitory;
import org.example.inventory.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
    private final SupplierReponsitory supplierReponsitory;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponse createSupplier(SupplierDTO supplier){
        log.info("createSupplier...............");
        Supplier supplierEntity = supplierMapper.toSupplier(supplier);
        Supplier savedSupplier = supplierReponsitory.save(supplierEntity);
        log.info("createSupplier successfully: " + savedSupplier.getName());
        return supplierMapper.toSupplierResponse(savedSupplier);
    }

    @Override
    public List<SupplierResponse> getSuppliersAll() {
        log.info("getSuppliersAll..............");
        List<SupplierResponse> suppliers = supplierReponsitory.findAll().stream()
                .map(supplierMapper::toSupplierResponse)
                .toList();
        log.info("getSuppliersAll successfully. Total suppliers: " + suppliers.size());
        return suppliers;
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {
        log.info("getSupplierById.............."+id);
        Supplier supplier = supplierReponsitory.findById(id).
                orElseThrow(() ->new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        log.info("getSupplierById successfully: " + supplier.getName());
        return supplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(SupplierDTO supplierDTO, Long id) {
        log.info("updateSupplier..............."+id);
        Supplier supplierEntity = supplierReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        supplierMapper.updateSupplierFromDTO(supplierDTO,supplierEntity);
        supplierReponsitory.save(supplierEntity);
        log.info("updateSupplier successfully: " + supplierEntity.getName());
        return supplierMapper.toSupplierResponse(supplierEntity);
    }

    @Override
    public void deleteSupplier(Long id) {
        log.info("delete Supplier with id: " + id);
        Supplier supplier = supplierReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        supplierReponsitory.delete(supplier);
        log.info("delete Supplier successfully: " + supplier.getName());
    }
}
