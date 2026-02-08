package org.example.inventory.mapper;

import org.example.inventory.dtos.request.TransactionUpdateDTO;
import org.example.inventory.dtos.respon.TransactionResponse;
import org.example.inventory.models.Transaction;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    // 1. Chỉ map các field cơ bản (tên, giá...), bỏ qua ID để map sau
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "product.id", target = "productId")
    TransactionResponse toResponse(Transaction transaction);


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
