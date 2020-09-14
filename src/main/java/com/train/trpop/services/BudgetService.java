package com.train.trpop.services;

import com.train.trpop.entities.Budget;
import com.train.trpop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BudgetService {
    List<Budget> getBudgetByDate(Date from, Date to);
    void postOneBudget(Budget budget);
    void deleteOneBudget(Budget budget);
    void putOneBudget(Budget budget);
}
