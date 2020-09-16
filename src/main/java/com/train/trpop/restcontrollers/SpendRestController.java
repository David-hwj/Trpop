package com.train.trpop.restcontrollers;


import com.train.trpop.entities.Spend;
import com.train.trpop.services.SpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//        System.out.println(json);
        JSONObject o= null;
        String type=null;
        double payment=0.0;
        Date date=new Date();
        String note=null;
        try {
            o = new JSONObject(json);
            System.out.println(o);
            JSONArray spends=o.getJSONArray("spends");
            for(int i=0;i<spends.length();i++){
                JSONObject jo=spends.getJSONObject(i);
                type=jo.getString("type");
                payment=jo.getDouble("payment");
                date=sdf.parse(jo.getString("date"));
                note=jo.getString("note");
                Spend spend=new Spend(type,payment,date,note);
                spendService.postSpend(spend);
            }
        } catch (JSONException | ParseException e) {
            return String.format("Insert failed");
        }
        return String.format("Insert Succ :%S",json);
    }

    @DeleteMapping("")
    public String deleteSpend(@RequestParam(name = "id", required = true) String id){
        spendService.deleteSpend(new Spend(id,null,0.0,null,null));
        return String.format("Del Succ: %s",id);
    }
    @GetMapping("/bill")
    public List<Spend> getBill() throws ParseException {
        Spend spend1=new Spend("",2.2,sdf.parse("2020-02-12"),"breakfast");
        Spend spend2=new Spend("",100,sdf.parse("2020-02-13"),"cell phone");
        Spend spend3=new Spend("",3,sdf.parse("2020-02-14"),"subway");
        List<Spend> list=new ArrayList<>();
        list.add(spend1);
        list.add(spend2);
        list.add(spend3);
        return list;
    }
}
