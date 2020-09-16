package com.train.trpop.services;

import com.train.trpop.TrpopApplication;
import com.train.trpop.entities.Spend;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SpendServiceImplTest {
    @Autowired
    SpendService spendService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext= SpringApplication.run(TrpopApplication.class);
        spendService=applicationContext.getBean(SpendService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSpendByDate() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date from=sdf.parse("2020-01-01");
        Date to=sdf.parse("2020-12-01");
        spendService.getSpendByDate(from,to);
    }

    @Test
    public void postSpend() {
        Spend spend=new Spend("test",0.0,new Date(),"test");
        spendService.postSpend(spend);
    }

    @Test
    public void deleteSpend() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date from=sdf.parse("2020-01-01");
        Date to=sdf.parse("2020-12-01");
        List<Spend> spendList=spendService.getSpendByDate(from,to);
        for(Spend s:spendList){
            if(s.getNote().equals("test")){
                spendService.deleteSpend(s);
            }
        }

    }

    @Test
    public void putSpend() {
    }
}