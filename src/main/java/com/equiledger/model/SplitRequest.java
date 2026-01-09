package com.equiledger.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @Min(value = 0)
    @Max(value = 100)
    private Double percentage;

    private BigDecimal calculatedAmount;
}
