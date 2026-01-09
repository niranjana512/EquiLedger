package com.equiledger.service;

import com.equiledger.model.CreateExpenseRequest;
import com.equiledger.model.Expense;
import com.equiledger.model.SplitRecord;
import com.equiledger.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepo;

    private final Map<String, SplitStrategy> strategyMap;

    @Transactional
    public Expense createExpense(CreateExpenseRequest request) {

        var strategy = strategyMap.get(request.getSplitType());

        strategy.calculateCustomSplits(request.getTotalAmount(), request.getSplits());

        var splitRecords = request.getSplits().stream()
                .map(s -> new SplitRecord(s.getUserId(), s.getCalculatedAmount()))
                .toList();

        var expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getTotalAmount());
        expense.setPaidByUserId(request.getPaidBy());
        expense.setSplits(splitRecords);

        return expenseRepo.save(expense);
    }
}
