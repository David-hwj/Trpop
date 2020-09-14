package com.train.trpop.restcontrollers;


import com.train.trpop.entities.Spend;
import com.train.trpop.services.SpendService;
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
@RequestMapping("/spend")
public class SpendRestController {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    SpendService spendService;

    @GetMapping("")
    public List<Spend> getByDate(@RequestParam(name = "from", required = true) String from,
                                 @RequestParam(name = "to", required = false,defaultValue = "") String to){
        System.out.println(from+"  "+to);
        Date f=new Date();
        Date t=new Date();
        try {
            f=sdf.parse(from);
            if(to!="") t=sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(f.toString()+" "+t.toString());
        return spendService.getSpendByDate(f,t);
    }

    @PostMapping("")
    public String postSpend(@RequestBody String json){
        JSONObject o= null;
        String type=null;
        double payment=0.0;
        Date date=new Date();
        String note=null;
        try {
            o = new JSONObject(json);
            type=o.getString("type");
            payment=o.getDouble("payment");
            date=sdf.parse(o.getString("date"));
            note=o.getString("note");
        } catch (JSONException | ParseException e) {
            return String.format("Insert failed");
        }
        Spend spend=new Spend(type,payment,date,note);
        spendService.postSpend(spend);
        return String.format("Insert Succ :%S",spend.toString());
    }

    @DeleteMapping("")
    public String deleteSpend(@RequestParam(name = "id", required = true) String id){
        spendService.deleteSpend(new Spend(id,null,0.0,null,null));
        return String.format("Del Succ: %s",id);
    }
}
