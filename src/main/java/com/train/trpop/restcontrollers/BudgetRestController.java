package com.train.trpop.restcontrollers;


import com.train.trpop.entities.Budget;
import com.train.trpop.entities.Category;
import com.train.trpop.repository.BudgetRepository;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    BudgetService budgetService;
    @Autowired
    BudgetRepository budgetRepository;
    @GetMapping("")
    public List<Budget> getBByDate(@RequestParam(name = "from", required = true) String from,
                                   @RequestParam(name = "to", required = false, defaultValue = "") String to) {
//        System.out.println(from+" "+to);
        Date f = new Date();
        Date t = new Date();
        try {
            f = sdf.parse(from);
            if(to!="") t = sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            return  null;
        }
//        System.out.println(f.toString()+" "+t.toString());
        return budgetService.getBudgetByDate(f,t);
    }

    @PostMapping("")
    public Budget postBudget(@RequestBody String json) {
        JSONObject o =null;
        String type = null;
        double budgetAmount = 0;
        Date month = null;
        try {
            o = new JSONObject(json);
            type = o.getString("type");
            budgetAmount = o.getDouble("budget");
            month = sdf.parse(o.getString("Month"));
        } catch (JSONException | ParseException e) {
            return null;
        }

        List<Budget> list = budgetRepository.findAll();
        for(Budget b:list) {
            if(b.getType().equals(type) && b.getDate().getMonth()==(month.getMonth()))
                return null;
        }
        Budget budget = new Budget(type,budgetAmount,month);
        budgetService.postOneBudget(budget);
        return budget;
    }

    @DeleteMapping("")
    public String deleteBudget(@RequestParam(name = "id", required = true) String id) {
        budgetService.deleteOneBudget(new Budget(id,null,0.0,null));
        return String.format("Del Succ： %s",id);
    }
}





















