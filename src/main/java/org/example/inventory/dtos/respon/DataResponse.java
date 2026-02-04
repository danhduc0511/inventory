package org.example.inventory.dtos.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//code,message,data
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {
    private int  code;
    private String message;
    private T data;
}
