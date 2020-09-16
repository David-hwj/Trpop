package com.train.trpop.services;

import com.train.trpop.TrpopApplication;
import com.train.trpop.entities.Budget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class BudgetServiceImplTest {
    @Autowired
    BudgetService budgetService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(TrpopApplication.class);
        budgetService = applicationContext.getBean(BudgetService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBudgetByDate() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date from=sdf.parse("2020-01-01");
        Date to=sdf.parse("2020-12-01");
        budgetService.getBudgetByDate(from,to);
    }

    @Test
    public void postOneBudget() {
        Budget budget = new Budget("test",0.0,new Date());
        budgetService.postOneBudget(budget);
    }

    @Test
    public void deleteOneBudget() throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date from=sdf.parse("2020-01-01");
        Date to=sdf.parse("2020-12-01");
        List<Budget> budgetList = budgetService.getBudgetByDate(from,to);
        for(Budget b:budgetList) {
            if(b.getType().equals("test"))
                budgetService.deleteOneBudget(b);
        }
    }

    @Test
    public void putOneBudget() {
    }
}