package com.equiledger.service.impl;

import com.equiledger.model.SplitRequest;
import com.equiledger.service.SplitStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component("EQUAL")
public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public void calculateCustomSplits(BigDecimal total, List<SplitRequest> requests) {
        var n = requests.size();
        var share = total.divide(BigDecimal.valueOf(n), 2, RoundingMode.DOWN);
        var remainder = total.subtract(share.multiply(BigDecimal.valueOf(n)));

        for (int i = 0; i < n; i++) {
            requests.get(i).setCalculatedAmount(i == 0 ? share.add(remainder) : share);
        }
    }

}
