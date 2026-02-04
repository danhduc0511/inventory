package org.example.inventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.inventory.dtos.respon.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalHandlingException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        // 2. Lấy danh sách lỗi
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        // 3. Xử lý message an toàn
        String errorMessage = errors.isEmpty() ? "Validation failed" : String.join(", ", errors);
        ErrorResponse errorRespon = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value()) // 400
                .error(errorMessage) // Trả về chuỗi lỗi gộp
                .path(request.getRequestURI())
                .build();
        // 4. Return đúng status 400
        return ResponseEntity.badRequest().body(errorRespon);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ProblemDetail> handleAppException(AppException exception, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        problemDetail.setTitle("Application Error");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("errors",List.of(exception.getMessage()));
        return ResponseEntity.badRequest().body(problemDetail);
    }
}

