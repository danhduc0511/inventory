package org.example.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.ProductDTO;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.dtos.respon.ProductResponse;
import org.example.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    //api  tao product
    @PostMapping("/create")
    public DataResponse<ProductResponse> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductResponse productResponse = productService.createProduct(productDTO);
        return DataResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(productResponse)
                .build();
    }
    //api get all product
    @GetMapping("/all")
    public DataResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getProductsAll();
        return DataResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all products successfully")
                .data(products)
                .build();
    }
    //api tim kiem product theo id
    @GetMapping("/{id}")
    public DataResponse<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return DataResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get product by id successfully")
                .data(productResponse)
                .build();
    }
    //api cap nhat product
    @PutMapping("/{id}")
    public DataResponse<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductResponse productResponse = productService.updateProduct(id, productDTO);
        return DataResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Update product successfully")
                .data(productResponse)
                .build();
    }
    //api xoa product
    @DeleteMapping("/{id}")
    public DataResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return DataResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Delete product successfully")
                .build();
    }
}
