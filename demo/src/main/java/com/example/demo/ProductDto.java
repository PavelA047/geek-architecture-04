package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    @Min(value = 100)
    @Max(value = 10000)
    @NotNull
    private BigDecimal cost;

    public ProductDto(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }
}
