package com.example.harshdeepharshdeep.barcode_reader.Service;

/**
 * Created by martinli on 2016-11-20.
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nutrient {
    private String name;
    private String unit;

    private double per_portion;
    private double per_day;
    private double per_hundred;

    public Nutrient() {
        name = "";
        unit ="";
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public double getPer_portion() {
        return per_portion;
    }
    public void setPer_portion(double per_portion) {
        this.per_portion = per_portion;
    }
    public double getPer_day() {
        return per_day;
    }
    public void setPer_day(double per_day) {
        this.per_day = per_day;
    }
    public double getPer_hundred() {
        return per_hundred;
    }
    public void setPer_hundred(double per_phundred) {
        this.per_hundred = per_phundred;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void update(JSONObject nutrients) {
        // TODO Auto-generated method stub
        System.out.println(nutrients);
        try {
            Object p = nutrients.get("per-day");
            if( !p.equals(null) ) setPer_day(Double.parseDouble(p.toString()));

            p = nutrients.get("unit");
            if( !p.equals(null) ) setUnit(p.toString());

            p = nutrients.get("per-hundred");
            if( !p.equals(null) ) setPer_hundred(Double.parseDouble(p.toString()));

            p=nutrients.get("name");
            if( !p.equals(null) ) setName(p.toString());

            p=nutrients.get("per-portion");
            if( !p.equals(null) ) setPer_portion(Double.parseDouble(p.toString()));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public String toString() {

        String ret = "No Info";
        if (per_day != 0){
            ret = "" + per_day;
        }
        if (per_hundred != 0){
            ret = "" + per_hundred;
        }
        if (per_portion != 0){
            ret = "" + per_portion;
        }
        return ret + " " + unit ;
    }

    public double getVal() {
        double ret = 0 ;
        if (per_day != 0){
            ret = per_day;
        }
        if (per_hundred != 0){
            ret = per_hundred;
        }
        if (per_portion != 0){
            ret = per_portion;
        }

        return ret;
    }

}