package org.example.inventory.mapper;

import org.example.inventory.dtos.request.ProductDTO;
import org.example.inventory.dtos.respon.ProductResponse;
import org.example.inventory.models.Product;
import org.mapstruct.*;

@Mapper(componentModel="spring")
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    @Mapping(source="category.name",target="categoryName")
    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductDTO productDTO,@MappingTarget Product product);
}
