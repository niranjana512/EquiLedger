package com.equiledger.service;

import com.equiledger.repository.SplitRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SplitRecordRepository splitRepository;

    public List<String> simplifyDebts(Map<String, BigDecimal> netBalances) {

        PriorityQueue<Map.Entry<String, BigDecimal>> creditors = new PriorityQueue<>((a, b)
                -> b.getValue().compareTo(a.getValue()));

        PriorityQueue<Map.Entry<String, BigDecimal>> debtors = new PriorityQueue<>(Map.Entry.comparingByValue());

        netBalances.forEach((user, bal) -> {
            if (bal.compareTo(BigDecimal.ZERO) > 0) creditors.add(Map.entry(user, bal));
            else if (bal.compareTo(BigDecimal.ZERO) < 0) debtors.add(Map.entry(user, bal));
        });

        List<String> results = new ArrayList<>();
        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            var creditor = creditors.poll();
            var debtor = debtors.poll();

            assert debtor != null;
            var settledAmount = creditor.getValue().min(debtor.getValue().abs());
            results.add(debtor.getKey() + " pays " + creditor.getKey() + ": " + settledAmount);

            var remainingCreditor = creditor.getValue().subtract(settledAmount);
            var remainingDebtor = debtor.getValue().add(settledAmount);

            if (remainingCreditor.signum() > 0)
                creditors.add(Map.entry(creditor.getKey(), remainingCreditor));
            if (remainingDebtor.signum() < 0)
                debtors.add(Map.entry(debtor.getKey(), remainingDebtor));
        }
        return results;
    }
}
