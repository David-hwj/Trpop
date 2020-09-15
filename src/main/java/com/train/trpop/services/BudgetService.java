package com.train.trpop.services;

import com.train.trpop.entities.Budget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BudgetService {
    List<Budget> getAllBudget();
    void postOneBudget(Budget budget);
    void deleteOneBudget(Budget budget);
    void putOneBudget(Budget budget);
}
