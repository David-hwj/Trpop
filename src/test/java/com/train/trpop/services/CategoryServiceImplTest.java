package com.train.trpop.services;

import com.train.trpop.TrpopApplication;
import com.train.trpop.entities.Category;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext=SpringApplication.run(TrpopApplication.class);
        categoryService=applicationContext.getBean(CategoryService.class);
    }

    @After
    public void tearDown() throws Exception {
        List<Category> categories=categoryService.getAllCategory();
        for(Category c:categories){
            if(c.getType().equals("test")){
                categoryService.deleteOneCategory( c );
            }
        }

    }

    @Test
    public void getAllCategory() {
        List<Category> ret=categoryService.getAllCategory();
        Assert.assertNotNull(ret);
    }

    @Test
    public void postOneCategory() {
        categoryService.postOneCategory(new Category("test"));
    }

    @Test
    public void deleteOneCategory() {
        List<Category> categories=categoryService.getAllCategory();
        for(Category c:categories){
            if(c.getType().equals("test")){
                categoryService.deleteOneCategory( c );
            }
        }
    }

    @Test
    public void putOneCategory() {
    }
}