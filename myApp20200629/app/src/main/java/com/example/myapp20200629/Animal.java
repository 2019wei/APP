package com.example.myapp20200629;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Animal implements Serializable {
    String subid ;
    String place;
    String kind;
    String img;
    String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Animal() {
    }

    public Animal(String subid, String place, String kind, String img) {
        this.subid = subid;
        this.place = place;
        this.kind = kind;
        this.img = img;
    }
    public Animal(JSONObject object){
        try {
            subid = object.getString("animal_subid");
            place = object.getString("animal_place");
            kind = object.getString("animal_kind");
            img = object.getString("album_file");
            colour = object.getString("animal_colour");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
