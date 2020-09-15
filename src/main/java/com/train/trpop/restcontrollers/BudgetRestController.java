package com.train.trpop.restcontrollers;


import com.train.trpop.entities.Budget;
import com.train.trpop.entities.Category;
import com.train.trpop.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/budget")
public class BudgetRestController {

    @Autowired
    BudgetService budgetService;

    @GetMapping("")
    public List<Budget> fetbudget() {
        List<Budget> list = budgetService.getAllBudget();
        return list;
    }

    @PostMapping("")
    public String postBudget(@RequestBody String json) {
        JSONObject o =null;
        String _id = null;
        String type = null;
        double budgetAmount = 0;
        Date month = null;
        try {
            o = new JSONObject(json);
//            _id = o.getString("_id");
            type = o.getString("type");
            budgetAmount = o.getDouble("budget");
            String tempMonth = o.getString("Month");
            SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
            month = tm.parse(tempMonth);
        } catch (JSONException | ParseException e) {
            return String.format("Insert failed");
        }

        List<Budget> list = budgetService.getAllBudget();
        for(Budget b:list) {
            if (b.getDate().equals(month)&&b.getType().equals(type) ) {
                return String.format("Budget already exist");
            }
        }

        Budget budget = new Budget(type,budgetAmount,month);
        budgetService.postOneBudget(budget);
        return String.format("Budget(type=%s,budget=%f,month=%tF) inserted",type,budgetAmount,month);
    }

    @DeleteMapping("")
    public String deleteBudget(@RequestBody String json) {
        JSONObject o = null;
        String _id = null;
        String type = null;
        double budgetAmount = 0;
        Date month = null;
        try {
            o = new JSONObject(json);
            _id = o.getString("_id");
            type = o.getString("type");
            budgetAmount = o.getDouble("budget");
            String tempMonth = o.getString("Month");
            SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
            month = tm.parse(tempMonth);
        } catch (JSONException | ParseException e) {
            return String.format("Del failed");
        }
        Budget budget = null;
        List<Budget> list = budgetService.getAllBudget();
        for(Budget b:list) {
            if (b.get_id().equals(_id) ) {
                budget = b;
            }
        }
        if(budget == null) return String.format("Del failed");
        budgetService.deleteOneBudget(budget);
        return  String.format("Budget(type=%s,budget=%f,month=%tF) deleted",type,budgetAmount,month);
    }
}





















