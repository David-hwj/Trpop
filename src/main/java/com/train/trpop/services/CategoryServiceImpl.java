package com.train.trpop.services;


import com.train.trpop.entities.Category;
import com.train.trpop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void postOneCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteOneCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void putOneCategory(Category category) {
        categoryRepository.save(category);
    }
}
