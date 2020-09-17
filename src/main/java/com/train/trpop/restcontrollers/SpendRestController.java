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
    public List<Spend> getBill(@RequestParam(name = "type", required = true) String type) throws ParseException {
//        System.out.println(type);
        List<Spend> list=new ArrayList<>();
        if(type.equals("1")){
            list.add(new Spend("",2.2,sdf.parse("2020-09-12"),"alipay"));
            list.add(new Spend("",100.0,sdf.parse("2020-09-13"),"alipay"));
            list.add(new Spend("",3.0,sdf.parse("2020-09-14"),"alipay"));
        }else if(type.equals("2")){
            list.add(new Spend("",2.2,sdf.parse("2020-09-02"),"wechatpay"));
            list.add(new Spend("",300.0,sdf.parse("2020-09-03"),"wechatpay"));
            list.add(new Spend("",1500.0,sdf.parse("2020-09-11"),"wechatpay"));
            list.add(new Spend("",5.0,sdf.parse("2020-09-17"),"wechatpay"));
        }else{
            list.add(new Spend("",0.0,sdf.parse("2020-09-02"),type));
        }
        return list;
    }
}
