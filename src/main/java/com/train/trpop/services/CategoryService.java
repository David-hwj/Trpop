package com.train.trpop.services;

import com.train.trpop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    void postOneCategory(Category category);
    void deleteOneCategory(Category category);
    void putOneCategory(Category category);
}
