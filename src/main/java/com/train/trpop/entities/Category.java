package com.train.trpop.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {
    private String _id;
    private String type;

    public Category() {
    }

    public Category(String type) {
        this.type = type;
    }

    public Category(String _id, String type) {
        this._id = _id;
        this.type = type;
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

    public String toString(){
        return String.format("ObjectId:%s type:%s",_id,type);
    }
}
