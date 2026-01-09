package com.equiledger.service;

import com.equiledger.model.SplitRequest;

import java.math.BigDecimal;
import java.util.List;

public interface SplitStrategy {

    void calculateCustomSplits(BigDecimal total, List<SplitRequest> requests);
}
