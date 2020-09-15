package com.train.trpop.services;


import com.train.trpop.entities.Spend;
import com.train.trpop.repository.SpendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpendServiceImpl implements SpendService{
    @Autowired
    SpendRepository spendRepository;

    @Override
    public List<Spend> getSpendByDate(Date from, Date to) {
        List<Spend> all=spendRepository.findAll();
        List<Spend> res=new ArrayList<Spend>();
        for(Spend b : all){
            if(b.getDate().after(from) && b.getDate().before(to)){
                res.add(b);
            }
        }
        return res;
    }

    @Override
    public void postSpend(Spend spend) {
        spendRepository.save(spend);
    }

    @Override
    public void deleteSpend(Spend spend) {
        spendRepository.delete(spend);
    }

    @Override
    public void putSpend(Spend spend) {
        spendRepository.save(spend);
    }
}
