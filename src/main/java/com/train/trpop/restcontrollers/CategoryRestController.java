package com.train.trpop.restcontrollers;


import com.train.trpop.entities.Category;
import com.train.trpop.services.CategoryService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> fetCategory(){
        List<Category> list=categoryService.getAllCategory();
        return list;
    }

    @PostMapping("")
    public String postCategory(@RequestBody String json) {
        JSONObject o= null;
        String type=null;
        try {
            o = new JSONObject(json);
            type=o.getString("type");
        } catch (JSONException e) {
            return String.format("Insert failed");
        }
        List<Category> list=categoryService.getAllCategory();
        for(Category c:list){
            if(c.getType().equals(type)){
                return String.format("Category already exist");
            }
        }
        Category category=new Category(type);
        categoryService.postOneCategory(category);
        return String.format("Category(type=%s) inserted",type);
    }

    @DeleteMapping("")
    public String deleteCategory(@RequestBody String json){
        JSONObject o= null;
        String type=null;
        try {
            o = new JSONObject(json);
            type=o.getString("type");
        } catch (JSONException e) {
            return String.format("Del failed");
        }
        Category category=null;
        List<Category> list=categoryService.getAllCategory();
        for(Category c:list){
            if(c.getType().equals(type)){
                category=c;
                break;
            }
        }
        if(category==null) return String.format("Del failed");
        categoryService.deleteOneCategory(category);
        return String.format("Category(type=%s) deleted",type);
    }
}
