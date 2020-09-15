package com.train.trpop.services;


import com.train.trpop.entities.Budget;
import com.train.trpop.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public List<Budget> getBudgetByDate(Date from, Date to) {
        List<Budget> all = budgetRepository.findAll();
        List<Budget> res = new ArrayList<Budget>();
        for(Budget b: all) {
            if(b.getDate().after(from) && b.getDate().before(to)) {
                res.add(b);
            }
        }
        return res;
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
