package org.example.inventory.service;

import org.example.inventory.dtos.request.ProductDTO;
import org.example.inventory.dtos.respon.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductDTO productDTO);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsAll();
    ProductResponse updateProduct(Long id,ProductDTO productDTO);
    void deleteProductById(Long id);
}
