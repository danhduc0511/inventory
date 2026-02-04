package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.ProductDTO;
import org.example.inventory.dtos.respon.ProductResponse;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.mapper.ProductMapper;
import org.example.inventory.models.Category;
import org.example.inventory.models.Product;
import org.example.inventory.repository.CategoryReponsitory;
import org.example.inventory.repository.ProductReponsitory;
import org.example.inventory.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductReponsitory productReponsitory;
    private final CategoryReponsitory categoryReponsitory;

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) {
        log.info("Creating product {}", productDTO);
        Category category = categoryReponsitory.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Product product = productMapper.toProduct(productDTO);
        product.setCategory(category);
        Product savedProduct = productReponsitory.save(product);
        log.info("Product {} has been saved successfully", savedProduct);
        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Getting product by id {}.............", id);
        ProductResponse productResponse = productReponsitory.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        log.info("get product by id {} successfully", id);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getProductsAll() {
        log.info("Getting all products from database......");
        List<ProductResponse> productResponses = productReponsitory.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
        log.info("Get all products successfully");
        return productResponses;
    }

    @Override
    @Transactional()
    public ProductResponse updateProduct(Long id,ProductDTO productDTO) {
        log.info("Updating product {}", productDTO);
        Product existingProduct = productReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Category category = categoryReponsitory.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        productMapper.updateProductFromDTO(productDTO,existingProduct);
        existingProduct.setCategory(category);
        Product updatedProduct = productReponsitory.save(existingProduct);
        log.info("Product {} has been updated successfully", updatedProduct.getName());
        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product {}", id);
        Product existingProduct = productReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productReponsitory.delete(existingProduct);
        log.info("Product with id {} has been deleted successfully",id);
    }
}
