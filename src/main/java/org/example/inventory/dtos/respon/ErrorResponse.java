package org.example.inventory.dtos.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//time,status,error,path
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
//@JsonInclude(JsonInclude.Include.NON_NULL):"Nếu trường nào có giá trị là NULL, thì đừng viết nó vào chuỗi JSON trả về."

