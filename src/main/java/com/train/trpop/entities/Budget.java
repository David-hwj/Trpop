package com.train.trpop.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "budget")
public class Budget {
    String _id;
    Date month;
    String type;
    double budget;

    public Budget() {
    }

    public Budget(Date month, String type, double budget) {
        this.month = month;
        this.type = type;
        this.budget = budget;
    }

    public Budget(String _id, Date month, String type, double budget) {
        this._id = _id;
        this.month = month;
        this.type = type;
        this.budget = budget;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
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
}
