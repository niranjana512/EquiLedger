package com.equiledger.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateExpenseRequest {

    @NotBlank(message = "ID is required")
    private String paidBy;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01")
    private BigDecimal totalAmount;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Split type is required (EQUAL, EXACT, PERCENTAGE)")
    private String splitType;

    @NotEmpty(message = "At least one participant is required")
    private List<SplitRequest> splits;

    private String expenseCategory;
}
