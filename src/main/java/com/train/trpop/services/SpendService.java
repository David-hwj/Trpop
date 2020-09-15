package com.train.trpop.services;


import com.train.trpop.entities.Spend;
import com.train.trpop.repository.SpendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SpendService {

    List<Spend> getSpendByDate(Date from, Date to);
    void postSpend(Spend spend);
    void deleteSpend(Spend spend);
    void putSpend(Spend spend);
}
