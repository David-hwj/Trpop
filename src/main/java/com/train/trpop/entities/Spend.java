package com.train.trpop.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("spend")
public class Spend {
    String _id;
    String type;
    double payment;
    Date date;
    String note;

    public Spend() {
    }

    public Spend(String type, double payment, Date date, String note) {
        this.type = type;
        this.payment = payment;
        this.date = date;
        this.note = note;
    }

    public Spend(String _id, String type, double payment, Date date, String note) {
        this._id = _id;
        this.type = type;
        this.payment = payment;
        this.date = date;
        this.note = note;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String toString(){
        return String.format("Spend(type=%s,payment=%.2f,date=%s,note=%s)",type,payment,date,note);
    }
}
