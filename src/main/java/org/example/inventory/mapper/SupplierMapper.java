package org.example.inventory.mapper;

import org.example.inventory.dtos.request.SupplierDTO;
import org.example.inventory.dtos.respon.SupplierResponse;
import org.example.inventory.models.Supplier;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierMapper {
    Supplier toSupplier(SupplierDTO supplierDTO);
    SupplierResponse toSupplierResponse(Supplier supplier);
    void updateSupplierFromDTO( SupplierDTO supplierDTO, @MappingTarget Supplier supplier);
}
