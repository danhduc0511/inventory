package org.example.inventory.mapper;

import org.example.inventory.dtos.request.TransactionUpdateDTO;
import org.example.inventory.dtos.respon.TransactionResponse;
import org.example.inventory.models.Transaction;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    // 1. Chỉ map các field cơ bản (tên, giá...), bỏ qua ID để map sau
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "supplierId", ignore = true)
    @Mapping(target = "productId", ignore = true)
    TransactionResponse toResponse(Transaction transaction);

    // 2. Logic map ID nằm gọn ở đây
    @AfterMapping
    default void mapIds(Transaction source, @MappingTarget TransactionResponse target) {
        if (source.getUser() != null) target.setUserId(source.getUser().getId());
        if (source.getSupplier() != null) target.setSupplierId(source.getSupplier().getId());
        if (source.getProduct() != null) target.setProductId(source.getProduct().getId());
    }


}
//Nó quét toàn bộ file TransactionMapper.
//
//Nó tìm thấy method mapIds có gắn @AfterMapping.
//
//Nó phân tích tham số của mapIds:
//
//Tham số 1: Transaction source (trùng với input của toResponse).
//
//Tham số 2: @MappingTarget TransactionResponse target (trùng với output của toResponse).
//
//        => Nó hiểu: "À, chủ nhân muốn chạy hàm mapIds này ngay trước khi return target trong hàm toResponse".
//
//        => Nó viết code Java chèn dòng gọi hàm đó vào vị trí tương ứng.
