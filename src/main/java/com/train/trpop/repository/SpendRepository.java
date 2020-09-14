package com.train.trpop.repository;

import com.train.trpop.entities.Spend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendRepository extends MongoRepository<Spend,String> {
}
