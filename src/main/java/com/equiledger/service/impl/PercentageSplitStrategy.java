package com.equiledger.service.impl;

import com.equiledger.model.SplitRequest;
import com.equiledger.service.SplitStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component("PERCENTAGE")
public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public void calculateCustomSplits(BigDecimal total, List<SplitRequest> requests) {
        var totalPercent = requests.stream()
                .map(r -> BigDecimal.valueOf(r.getPercentage()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPercent.compareTo(new BigDecimal("100")) != 0)
            throw new RuntimeException("Percentage must sum to 100");

        for (var r : requests) {
            r.setCalculatedAmount(total.multiply(BigDecimal.valueOf(r.getPercentage()))
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
        }
    }

}