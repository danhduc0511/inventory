package org.example.inventory.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_EXIST(400, "User does not exist", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(400, "Invalid username or password", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "Unauthorized access", HttpStatus.UNAUTHORIZED),
    CATEGORY_NOT_FOUND(404, "Category not found", HttpStatus.NOT_FOUND),
    SUPPLIER_NOT_FOUND(404, "Supplier not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(404, "Product not found", HttpStatus.NOT_FOUND),
    INSUFFICIENT_STOCK(400, "Insufficient stock for the product", HttpStatus.BAD_REQUEST),
    TRANSACTION_NOT_FOUND(404, "Transaction not found", HttpStatus.NOT_FOUND),;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
//UNAUTHORIZED :Chưa đăng nhập, hoặc đăng nhập thất bại.(401)
//ACCESS_DENIED :Đã đăng nhập nhưng không có quyền truy cập tài nguyên.(403)
// INSUFFICIENT_STOCK:Sản phẩm trong kho không đủ để thực hiện giao dịch.(400)