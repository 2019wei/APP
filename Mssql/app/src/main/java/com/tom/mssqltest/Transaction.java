package com.tom.mssqltest;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Transaction {
    String account;
    String date;
    String amount;
    String type;

//    public Transaction(JSONObject object) {
//        try {
//            account = object.getString("account");
//            date = object.getString("date");
//            amount = object.getString("amount");
//            type = object.getString("type");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
