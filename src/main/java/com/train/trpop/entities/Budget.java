package com.train.trpop.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "budget")
public class Budget {
    private String _id;
    private String type;
    private double budget;
    private Date month;

    public Budget() {
    }

    public Budget(String type, double budget, Date month) {
        this._id = _id;
        this.type = type;
        this.budget = budget;
        this.month = month;
    }

    public Budget(String _id, String type, double budget, Date month) {
        this._id = _id;
        this.type = type;
        this.budget = budget;
        this.month = month;
    }

    public String get_id() { return _id;}

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getDate() {
        return month;
    }

    public void setDate(Date month) { this.month = month; }

    public String toString(){
        return String.format("ObjectId:%s type:%s budget:%f Date:%tF",_id,type,budget,month);
    }
}
