package com.domain.expansion.productservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEditProduct {

    @NonNull
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
