package com.domain.expansion.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

    private List<T> data;
    private String message;
    private boolean error;
    private HttpStatus status;
}
