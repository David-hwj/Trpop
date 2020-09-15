package com.train.trpop.services;


import com.train.trpop.entities.Budget;
import com.train.trpop.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public List<Budget> getAllBudget() {
        return budgetRepository.findAll();
    }

    @Override
    public void postOneBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public void deleteOneBudget(Budget budget) {
        budgetRepository.delete(budget);
    }

    @Override
    public void putOneBudget(Budget budget) {
        budgetRepository.save(budget);
    }
}
